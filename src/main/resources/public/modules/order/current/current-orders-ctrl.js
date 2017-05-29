'use strict';
creativei_app.controller('CurrentOrdersController', function($scope,$http, $state, $localStorage, _, OrderService, RestaurantTables, ActiveOrders){
  console.log("Inside current orders controller.");


    //my changes
    //filter implementation
    $scope.tables = [];
    $localStorage.currentTable = "";
    $scope.tableFilterOptions = ['All', 'Available', 'Occupied'];
    $scope.tableFilter = $scope.tableFilterOptions[0];

    _.each(RestaurantTables, function(table){
      var order = _.findWhere(ActiveOrders, {tableId : table.id});
      if(order){
        var table = {
          id: table.id,
          tableNumber: table.tableNumber,
          occupancy: table.occupancy || 4,
          isAvailable: false,
          status: order.state,
          tableOccupiedDuration: "1:00Hrs",
          order : order
        };
      }else{
        var table = {
          id: table.id,
          tableNumber: table.tableNumber,
          occupancy: table.occupancy || 4,
          isAvailable: true,
          status: 'Available',
          order : {}
        };
      }
      $scope.tables.push(table);
    });


    $scope.tableFilterFunction = function (element) {
        if ($scope.tableFilter == 'All') {
            return true;
        } else if ($scope.tableFilter == 'Occupied') {
            if (element.isAvailable) {
                return false;
            } else {
                return true;
            }
        } else if ($scope.tableFilter == 'Available') {
            if (element.isAvailable) {
                return true;
            } else {
                return false;
            }
}
    };
    $scope.assign = function(tableId){
      $localStorage.currentTable = tableId;
      $state.go('buildOrder.menuItem');
    }

    $scope.manage = function(id, tableId){
      $localStorage.currentTable = tableId;
      $state.go('buildOrder.trackOrder', {id : id});
    }
    //end filter implementation
    //dummy json for tables
    // $http.get("../../../commons/JSONs/tableStatus.json")
    // .then(function(response) {
    //     $scope.tables = response.data;
    // });
});

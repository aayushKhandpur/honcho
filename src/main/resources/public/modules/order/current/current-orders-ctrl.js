'use strict';
creativei_app.controller('CurrentOrdersController', function($scope,$http, $state, $localStorage, _, RestaurantTables, ActiveOrders){
  console.log("Inside current orders controller.");


    //my changes
    //filter implementation
    $scope.tables = [];
    $localStorage.currentTable = "";
    $scope.tableFilterOptions = ['All', 'Available', 'Occupied'];
    $scope.tableFilter = $scope.tableFilterOptions[0];

    _.each(RestaurantTables, function(table){
      var order = _.find(ActiveOrders, function(order){
        order.tableId == table.id;
      });
      if(order){
        var table = {
          id: table.id,
          tableNumber: table.tableNumber,
          occupancy: table.occupancy,
          isAvailable: false,
          status: order.orderState,
          tableOccupiedDuration: "1:00Hrs",
          order : order
        };
      }else{
        var table = {
          id: table.id,
          tableNumber: table.tableNumber,
          occupancy: table.occupancy,
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
    $scope.assign = function(tableNumber){
      $localStorage.currentTable = tableNumber;
      $state.go('buildOrder.menuItem');
    }
    //end filter implementation
    //dummy json for tables
    // $http.get("../../../commons/JSONs/tableStatus.json")
    // .then(function(response) {
    //     $scope.tables = response.data;
    // });
});

//
creativei_app.controller("ServicesController", function ($scope, $state,$localStorage, Services) {
    $scope.services = Services;
    //dummy comment
    $scope.selectService = function(service){
      switch (service) {
        case 'ORDER':
          $state.go('order.current');
          break;
        default:
          console.log("no state selected.");
      }
    };
});

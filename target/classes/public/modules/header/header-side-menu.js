'use strict';
creativei_app.controller('SideMenuController', function ($scope, $state){
    $scope.openNav = function() {
        document.getElementById("mySidenav").style.width = "320px";
        document.getElementById("main").style.marginLeft = "320px";
    };

    $scope.closeNav = function() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    };

    $scope.switchService = function(service){
      switch (service) {
        case 'ORDER':
          $state.go('order.current');
          break;
          case 'services':
            $state.go('order.current');
            break;
          case 'LOGOUT':
            $state.go('logout');
            break;
        default:
          $state.go('services');
      }

    }



});

'use strict';
creativei_app.controller('OrderController', function ($scope, $rootScope, $localStorage, CategoryService) {
    $scope.order = "Inside order controller";

    function getMenuItems(){
      CategoryService.getCategories()
            .then(function(response){
              if(response.data.status =="ERROR"){
                console.log(response.data.exception.errorCode +" : " + response.data.exception.message);
              }else if(response.data.data !== undefined){
                $localStorage.categories = response.data.data;
                var menuItemList = [];
                angular.forEach(response.data.data, function(category, key){
                  if(category.menuItems && category.menuItems.length >0){
                    menuItemList.push.apply(menuItemList, category.menuItems);
                  }
                });
                if(menuItemList.length > 0) $localStorage.menuItemList = menuItemList;
              }
        },function(e){
            console.log(e);
      });
    }
    getMenuItems();

});

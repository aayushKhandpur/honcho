creativei_app.controller('MenuItemController', function ($scope, $filter, $uibModal, $stateParams
  , $http, $state, $localStorage, $anchorScroll, $location, CartService, OrderService, _, categories, CurrentOrder) {
    console.log("Inside menu item controller.");
    $scope.tableId = $localStorage.currentTable;
    //TODO set $scope.order

    // $scope.order = (CurrentOrder == null || CurrentOrder === {}) ?  : CurrentOrder;

    if(CurrentOrder === null || CurrentOrder == {} || CurrentOrder.items == null){
      $scope.order = getNewOrder();
    }else{
      $scope.order = CurrentOrder;
    }
    // if($localStorage.runningOrders && $localStorage.runningOrders != {}){
    //   $scope.order = $localStorage.runningOrders[$scope.tableId] || getNewOrder();
    // }else {
    //   $scope.order = getNewOrder();
    // }
    $scope.categories = categories;
    $scope.selectedCategory = $scope.categories[0];
    $scope.cartSize = 0;
    console.log($scope.categories);
    $scope.menuItemList = [];
    $scope.cartItems =[];
    //$scope.subtotal = CartService.updateSubTotal($scope.cartItems);
    if(categories !== undefined){
      angular.forEach($scope.categories, function(category, key){
        if(category.menuItems && category.menuItems.length >0){
          $scope.menuItemList.push.apply($scope.menuItemList, category.menuItems);
        }
      });

    }
    //TODO sync cart and menu in case there is already an order
    if($scope.order.items.length > 0) syncMenuItemAndCartWithRoot();
    // if($localStorage.runningOrders
    //   && $localStorage.runningOrders[$scope.tableId]
    //   && $localStorage.runningOrders[$scope.tableId].items){
    //   syncMenuItemAndCartWithRoot();
    // }
    $localStorage.menuItemList = $scope.menuItemList;

    $scope.$watch('query.name', function(newValue, oldValue) {
      angular.forEach($scope.categories, function(category, key){
        var filteredMenu = $filter('filter')(category.menuItems, $scope.query);
        if(filteredMenu.length==0){
          category.visible = false;
        }else{
          category.visible = true;
        }
      });
    });
    //add or update menu item and sync order item in cart.
    $scope.addItem = function(menuItem,type){
      var qty = 1;
      switch (type) {
        case "ADD":{
          menuItem.quantity += qty;
        }
        break;
        case "REMOVE":{
          menuItem.quantity -= qty;
        }
        break;
        default:
          return;
      }
      var response = CartService.addItem(menuItem, $scope.order.items, $scope.tableId);
      if(response.cart && response.cart != ""){
        $scope.order.items = response.cart;
        $scope.subtotal = CartService.updateSubTotal($scope.order.items);
      }
    };
    //update order item in cart and sync menu item menuItemId
    $scope.updateOrderItemAndSync = function(orderItem,type){
      var qty = 1;
      switch (type) {
        case "ADD":{
          orderItem.quantity += qty;
        }
        break;
        case "REMOVE":{
          orderItem.quantity -= qty;
        }
        break;
        default:
          return;
      }
      // update rootScope
      var cartItem = _.find($scope.order.items, function(item){ return item.menuItemId === orderItem.menuItemId });
      if(cartItem){
        if(orderItem.quantity == 0){
          $scope.order.items.splice($scope.order.items.indexOf(cartItem), 1);
        }else {
          cartItem.quantity = orderItem.quantity;
          cartItem.price = orderItem.quantity * orderItem.rate;
        }
      }
      //CartService.updateItem(orderItem.menuItemId, orderItem.quantity, orderItem.rate, $scope.tableId);
      angular.forEach($scope.categories, function(category, key){
        angular.forEach(category.menuItems, function(menuItem, key){
          if(menuItem.id === orderItem.menuItemId){
            menuItem.quantity = orderItem.quantity;
          }
        });
      });
      $scope.subtotal = CartService.updateSubTotal($scope.order.items);
    //if(orderItem.quantity == 0) delete $scope.cartItems[orderItem.id];
    };
    function syncMenuItemAndCartWithRoot(){
      //var items = $localStorage.runningOrders[$scope.tableId].items;
      var items = $scope.order.items;
      for(index in items){
        var orderItem = items[index];
        angular.forEach($scope.categories, function(category, key){
          angular.forEach(category.menuItems, function(menuItem, key){
            if(menuItem.id === orderItem.menuItemId){
              menuItem.quantity = orderItem.quantity;
            }
          });
        });
      }
      //$scope.order.items = $localStorage.runningOrders[$scope.tableId].items;
      $scope.subtotal = CartService.updateSubTotal($scope.order.items);
    }

    //scroll function for the category dropdown
    $scope.gotoAnchor = function(category) {
        var newHash = category.id;
        if ($location.hash() !== category.name) {
            // set the $location.hash to `newHash` and
            // $anchorScroll will automatically scroll to it
            $location.hash(category.name);
        } else {
            // call $anchorScroll() explicitly,
            // since $location.hash hasn't changed
            $anchorScroll();
        }
    };

    $scope.$watch('selectedCategory',function(newValue,oldValue){
        $anchorScroll('anchor'+ newValue.id);
    //    $scope.gotoAnchor(newValue);
    });
    function getNewOrder(){
      return {
        tableId : $scope.tableId,
        id: null,
        user  : "",
        customize : "",
        items : []
      };
    }
    $scope.confirmOrder = function(){
        //sync localStorage
        $scope.order.subtotal = $scope.subtotal;
        var data = $scope.order;
        OrderService.saveOrder(data).then(function(response){
          if(response.data.status == 'ERROR')
          {
            console.error();(response.data.exception.errorCode +" : " + response.data.exception.message);
          }else{
            console.log("Order created.");
            // if(!$localStorage.runningOrders) $localStorage.runningOrders = {};
          //   if(!$localStorage.runningOrders[$scope.tableId]){
          // //    $scope.order.items = $scope.order.items;
          //     $localStorage.runningOrders[$scope.tableId] = $scope.order;
          //   }
          //  $localStorage.runningOrders[$scope.tableId].items = $scope.order.items;
            $state.go('buildOrder.trackOrder', {id : response.data.data.id, order : response.data.data});
          }
        });

    }
});

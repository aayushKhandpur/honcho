creativei_app.controller('OrderTrackerController', function($scope,$state, $rootScope, $localStorage, CartService, OrderService, Order){
  console.log("Inside order tracker controller.");
  $scope.tableId = $localStorage.currentTable;
  $scope.order = Order;
  //$scope.cartItems = $localStorage.runningOrders[$scope.tableId].items;
  //$scope.cartItems = Order.items;
  $scope.order.subtotal = CartService.updateSubTotal($scope.order.items);


    $scope.countrySelected = function(selected) {
      if (selected) {
        window.alert('You have selected ' + selected.title);
      } else {
        console.log('cleared');
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
      $scope.order.subtotal = CartService.updateSubTotal($scope.order.items);
    //if(orderItem.quantity == 0) delete $scope.cartItems[orderItem.id];
    };

    $scope.updateOrder = function(){
        $scope.order.subtotal = CartService.updateSubTotal($scope.order.items);
        var data = $scope.order;
        OrderService.saveOrder(data).then(function(response){
          if(response.data.status == 'ERROR')
          {
            console.error();(response.data.exception.errorCode +" : " + response.data.exception.message);
          }else if(response.data.data.state == 'CANCEL'){
            $state.go('order.current');
          }else{
            console.log("Order updated.");
            $scope.order = response.data.data;
            $scope.order.subtotal = CartService.updateSubTotal($scope.order.items);
          }
        });
    }

    $scope.focusState = 'None';
    $scope.focusIn = function() {
      var focusInputElem = document.getElementById('trackCartSearch_value');
      $scope.focusState = 'In';
      focusInputElem.classList.add('trackCartSearchFull');
    }
    $scope.focusOut = function() {
      var focusInputElem = document.getElementById('trackCartSearch_value');
      $scope.focusState = 'Out';
      focusInputElem.classList.remove('trackCartSearchFull');
    }

    $scope.menuItems = $localStorage.menuItemList;

    $scope.toMenu = function(){
      $state.go('buildOrder.menuItem', {id : $scope.order.id});
    };
});

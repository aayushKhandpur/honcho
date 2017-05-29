creativei_app.factory('OrderService', function($http){
  return {
    getActiveOrders: function(){
      return $http({
            method: 'GET',
            url: baseUrl + '/orders/active',
            headers: {'Content-Type': 'application/json'}
        });
    },
    saveOrder: function(order){
      if(order.id != null && order.id != ""){
        return $http({
          method: 'PUT',
          url: baseUrl + '/order/update',
          data: order,
          headers: {'Content-Type': 'application/json'}
        });
      }else{
        return $http({
          method: 'POST',
          url: baseUrl + '/order/save',
          data: order,
          headers: {'Content-Type': 'application/json'}
        });
      }
    },
    getOrder: function(orderId){
      return $http({
        method: 'GET',
        url: baseUrl + '/order/'+orderId,
        headers: {'Content-Type': 'application/json'}
      });
    }
  }
});

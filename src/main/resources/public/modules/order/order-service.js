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
      return $http({
        method: 'POST',
        url: baseUrl + '/order/save',
        data: order,
        headers: {'Content-Type': 'application/json'}
      });
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

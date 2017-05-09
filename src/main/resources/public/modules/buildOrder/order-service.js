creativei_app.factory('OrderService', function($http, $rootScope, $localStorage){
  return {
    getActiveOrders: function(){
      return $http({
            method: 'GET',
            url: baseUrl + '/order/active',
            headers: {'Content-Type': 'application/json'}
        });
    }
  }
});

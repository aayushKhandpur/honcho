var baseUrl = window.location.protocol + '//' + window.location.host;
var creativei_app= angular.module("creativei_app",['ui.router','ngStorage','ui.bootstrap','ngAnimate', 'angucomplete-alt', 'angular-loading-bar'])
creativei_app.constant('_',
    window._
);

creativei_app.config(function($stateProvider,$urlRouterProvider) {
  $stateProvider
    .state('services', {
      url: '/services',
      templateUrl: 'modules/services/services.view.html',
      controller: 'ServicesController',
      resolve: {
        Services : function($http) {
          return $http.get(baseUrl + '/restaurant/services')
                .then(function(response) {
                  if(response.data.data){
                    return response.data.data
                  }else{
                    //console.log(response.data.exception.status+": "+response.data.exception.message);
                    return [];
                  }
                },function(response){
                  console.log("Unexpected error occured.");
                  return [];
                });
        }
      }

    })
    .state('order', {
      url: '/order',
      templateUrl: 'modules/order/order.view.html',
      controller: 'OrderController',
      abstract: true
    })
    .state('order.current', {
      url: '/current',
      templateUrl: 'modules/order/current/currentOrders.view.html',
      controller: 'CurrentOrdersController',
      resolve:{
        RestaurantTables :function(RestaurantService){
          return RestaurantService.getTables()
                .then(function(response){
                  if(response.data.status =="ERROR"){
                    console.log(response.data.exception.errorCode +" : " + response.data.exception.message);
                    return [];
                  }
                 return response.data.data;
            },function(e){
                console.log(e);
                return [];
          });
        },
        ActiveOrders : function(OrderService){
          return OrderService.getActiveOrders()
                .then(function(response){
                  if(response.data.status =="ERROR"){
                    console.log(response.data.exception.errorCode +" : " + response.data.exception.message);
                    return [];
                  }
                 return response.data.data;
            },function(e){
                console.log(e);
                return [];
          });
        }
      }
    })
    .state('home', {
      url: '/',
      //templateUrl: 'login.html',
      controller: 'LoginCtrl'

    })
    .state('buildOrder',{
      url: '/buildOrder',
      templateUrl: 'modules/buildOrder/buildOrder.view.html',
      controller: 'BuildOrderController',
      abstract: true
    })
    .state('buildOrder.initiate',{
      url: '/initiate',
      templateUrl: 'modules/buildOrder/orderInit/orderInitiation.view.html',
      controller: 'OrderInitiateController'
    })
    .state('buildOrder.category',{
      url: '/category',
      templateUrl: 'modules/buildOrder/category/category.view.html',
      controller: 'CategoryController'
    })
    .state('buildOrder.menuItem',{
      url: '/menuItem/:id/:categoryName',
      templateUrl: 'modules/buildOrder/category/menuItem/menuItem.view.html',
      controller: 'MenuItemController',
      params : {
        order : null
      },
      resolve:{
        categories :function(CategoryService, $localStorage){
          if($localStorage.categories != null && $localStorage.categories.length > 0) return $localStorage.categories;
          return CategoryService.getCategories()
                .then(function(response){
                  if(response.data.status =="ERROR"){
                    console.log(response.data.exception.errorCode +" : " + response.data.exception.message);
                    return [];
                  }
                 return response.data.data;
            },function(e){
                console.log(e);
                return [];
          });
        },
        CurrentOrder :function($stateParams, OrderService){
          var order = $stateParams.order || {};
          var id  = $stateParams.id;
          if(order.id && order.id !== null && order.id !== "") return order;
          if(id === undefined || id == null || id === "") return {};
          return OrderService.getOrder(id)
                  .then(function(response){
                    if(response.data.status =="ERROR"){
                      console.log(response.data.exception.errorCode +" : " + response.data.exception.message);
                      return {};
                    }
                   return response.data.data;
                  }, function(e){
                    console.log(e);
                    return {};
                  });
        }
      }
    })
    .state('buildOrder.trackOrder',{
      url: '/trackOrder/:id',
      templateUrl: 'modules/buildOrder/trackOrder/trackOrder.view.html',
      controller: 'OrderTrackerController',
      params : {
        order : null
      },
      resolve : {
        Order : function($stateParams, OrderService){
          var order = $stateParams.order || {};
          var id  = $stateParams.id;
          if(order.id && order.id !== null && order.id !== "") return order;
          return OrderService.getOrder(id)
                  .then(function(response){
                    if(response.data.status =="ERROR"){
                      console.log(response.data.exception.errorCode +" : " + response.data.exception.message);
                      return {};
                    }
                   return response.data.data;
                  }, function(e){
                    console.log(e);
                    return {};
                  });
        }

      }
    })
    .state('buildOrder.feedback', {
        url: '/feedback',
        templateUrl: 'modules/buildOrder/feedback/feedback.view.html',
        controller: 'feedbackController'
    })
    .state('buildOrder.thankyou', {
        url: '/thankyou',
        templateUrl: 'modules/buildOrder/thankyou/thankyou.view.html',
        controller: 'thankyouController'
    })
    .state('logout', {
        url: '/logout',
        templateUrl: 'modules/logout/logout.view.html',
        controller: 'logoutController'
    })
    .state('kot', {
        url: '/kot',
        templateUrl: 'modules/kot/kot.view.html',
        controller: 'KOTController'
    });
    //$urlRouterProvider.otherwise('/login');


});

// Login.js
creativei_app.controller('LoginCtrl', function($scope, $rootScope,$window,  $http) {
    console.log("inside login controller..");  
});

creativei_app.controller("MainController",function($scope, $rootScope, $state, $location, $localStorage, $http){
  $scope.$storage = $localStorage;

  //sync running orders with rootScope
  // if($localStorage.runningOrders){
  //   if($rootScope.runningOrders === undefined || $rootScope.runningOrders === {}){
  //     $rootScope.runningOrders = $localStorage.runningOrders;
  //   }
  // }else{
  //   $localStorage.runningOrders = {};
  // }

  $rootScope.$on('$stateChangeStart',
  function(event, toState, toParams, fromState, fromParams, options){
    if(toState.name == "logout"){
      delete $rootScope.isAuthenticated;
      delete $scope.$storage.isAuthenticated;
      // delete $rootScope.runningOrders;
      // delete $scope.$storage.runningOrders;
      return;
    }
    // if(toState.name === "login"){
    //   if($scope.$storage.isAuthenticated){
    //     $rootScope.isAuthenticated = $scope.$storage.isAuthenticated;
    //     $location.path("/services");
    //     return;
    //   }
    // }

    if($rootScope.isAuthenticated)
      return;
    if($scope.$storage.isAuthenticated){
      $rootScope.isAuthenticated = $scope.$storage.isAuthenticated;
      return;
    }
        // console.log("GET USER..")
        // $http({
        //     method:'GET',
        //     url:'/user',
        // })
        //     .success(function(data) {
        //         console.log(data);
        //         $rootScope.isAuthenticated = $scope.$storage.isAuthenticated = true;
        //         $state.go('services');
        //     })
        //     .error(function(data) {
        //         alert("error");
        //         console.log(data);
        //     });
  });
});

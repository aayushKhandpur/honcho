creativei_app.directive('buildOrderHeader',function(){
    return {
        restrict : 'A',
        templateUrl : 'modules/header/build-order-header.html',
        controller : "SideMenuController",
        replace : true
    }
});

'use strict';

// Register `phoneList` component, along with its associated controller and
// template
angular.module('phoneList').component('phoneList', {
	templateUrl : 'app/components/accm/accm-list.template.html',
	controller : [ 'Phone', '$scope', function PhoneListController(Phone, $scope) {
		// this.phones = Phone.query();
//		this.phones = Phone.getAccounts();
//		this.phones = null;
//		Phone.getAccounts(function(dataResponse) {
//			this.phones = dataResponse;
//	    });
		this.phones =null;
		$scope=null;
//		var handleSuccess = function(data, status) {
//			this.phones = data;
//	    };
//	    
//	    Phone.getAccounts().success(handleSuccess);
	    
		getAccounts(this);
	    
	    function getAccounts(ctrl) {
	    	Phone.getAccounts()
	            .then(function (response) {
	            	ctrl.phones = response.data;
	            }, function (error) {
	                $scope.status = 'Unable to load customer data: ' + error.message;
	            });
	    }
		
	    this.orderProp = 'age';
	} ]
});
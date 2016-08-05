'use strict';

angular.module('core.phone').factory('Phone', [ '$http', function($http) {

	var urlBase = '/accm/web/account';
	var Phone = {};

	Phone.getAccounts = function() {
		return $http.get(urlBase + "/list");
	};
	
	Phone.getAccount = function(id) {
		return $http.get(urlBase + '/' + id.phoneId);
	};

	Phone.getCustomer = function(id) {
		return $http.get(urlBase + '/' + id);
	};

	Phone.addAccount = function(cust) {
		if(cust.dateOfBirth){
			cust.dateOfBirth.setHours(cust.dateOfBirth.getHours() - cust.dateOfBirth.getTimezoneOffset() / 60);
		}
		return $http.post(urlBase + '/new', cust);
	};

	Phone.updateCustomer = function(cust) {
		return $http.put(urlBase + '/' + cust.ID, cust)
	};

	 Phone.deleteCustomer = function (id) {
		 return $http.delete(urlBase + '/' + id);
	 };

	Phone.getOrders = function(id) {
		return $http.get(urlBase + '/' + id + '/orders');
	};

	return Phone;
} ]);


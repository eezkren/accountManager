'use strict';

angular.module('core.account').factory('Account', [ '$http', function($http) {

	var urlBase = '/accm/web/account';
	var Account = {};

	Account.getAccounts = function() {
		return $http.get(urlBase + "/list");
	};
	
	Account.getAccount = function(id) {
		return $http.get(urlBase + '/' + id.accountId);
	};

	Account.addAccount = function(cust) {
		if(cust.dateOfBirth){
			cust.dateOfBirth.setHours(cust.dateOfBirth.getHours() - cust.dateOfBirth.getTimezoneOffset() / 60);
		}
		return $http.post(urlBase + '/new', cust);
	};

	Account.updateAccount = function(cust) {
		return $http.put(urlBase + '/' + cust.id, cust)
	};

	 Account.deleteCustomer = function (id) {
		 return $http.delete(urlBase + '/' + id);
	 };
	 
	 Account.getHistory = function(id) {
			return $http.get(urlBase + '/' + id.accountId + "/history");
		};

	return Account;
} ]);


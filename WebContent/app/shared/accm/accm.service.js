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

// angular.module('core.phone').factory('Phone', function($http) {
//
// // this.getAccounts = function(callbackFunc) {
// // // return $http.get('/accm/web/account/list').success(function(data) {
// // // }).error(function() {
// // // alert("error");
// // // });
// // // ;
// //
// // $http.get('/accm/web/account/list').success(function(data) {
// // // With the data succesfully returned, call our callback
// // callbackFunc(data);
// // }).error(function() {
// // alert("error");
// // });
// // }
//
// // return {
// // getAccounts: function() {
// // $http.get('/accm/web/account/list');
// // }
// // };
//
// return {
// getAccounts : function methodThatDoesAThing(callbackFunc) {
//
// $http.get('/accm/web/account/list').success(function(data) {
// // With the data succesfully returned, call our callback
// callbackFunc(data);
// }).error(function() {
// alert("error");
// });
// }
// };
// });

// angular.module('core.phone').factory('Phone',
// [ '$resource', function($resource) {
//
// console.log("1");
//
// return $resource('app/phones/:phoneId.json', {}, {
// query : {
// method : 'GET',
// params : {
// // phoneId : 'phones'
// phoneId : 'accounts'
// },
// isArray : true
// }
//
// });
// } ]);

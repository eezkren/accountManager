'use strict';

// Register `phoneDetail` component, along with its associated controller and
// template
angular.module('phoneEdit').component('phoneEdit', {
	templateUrl : 'app/components/accm/accm-edit.template.html',
	controller : [ '$routeParams', 'Phone',

	function PhoneEditController($routeParams, Phone) {

		getAccount(this);

		function getAccount(ctrl) {
			Phone.getAccount({
				phoneId : $routeParams.phoneId
			}).then(function(response) {
				ctrl.phone = response.data;
			}, function(error) {
				console.log("ERROR PhoneEditController")
				// $scope.status = 'Unable to load customer data: '
				// + error.message;
			});
		}

	}
	// , function PhoneNewController($routeParams) {
	//
	// var self = this;
	// self.phone = {};
	//
	// }
	]
});

'use strict';

// Register `phoneDetail` component, along with its associated controller and
// template
angular.module('phoneEdit').component('phoneEdit', {
	templateUrl : 'app/components/accm/accm-edit.template.html',
	controller : [ '$routeParams', 'Phone', 'Notification',

	function PhoneEditController($routeParams, Phone, Notification) {

		var ctrl = this;

		getAccount(this);

		ctrl.phone = {};
		ctrl.popup2 = {}

		function getAccount(ctrl) {
			Phone.getAccount({
				phoneId : $routeParams.phoneId
			}).then(function(response) {
				ctrl.phone = response.data;
				ctrl.phone.dateOfBirth = new Date(ctrl.phone.dateOfBirth);
			}, function(error) {
				console.log("ERROR PhoneEditController")
			});
		}

		ctrl.updateAccount = function updateAccount(phone) {
			Phone.addAccount(phone).then(function(response) {
				phone = response.data;
				ctrl.success();
			}, function(error) {
				console.log("ERROR PhoneNewController")
			});
		},

		ctrl.success = function success() {
			var message = '<strong>Account Updated</strong>';

			Notification.success({
				message : message,
				delay : 3000
			});
		},

		ctrl.open2 = function open2() {
			ctrl.popup2.opened = true;
		}

	} ]
});

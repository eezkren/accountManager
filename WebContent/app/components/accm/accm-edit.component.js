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
		ctrl.datepicker = {}

		function getAccount(ctrl) {
			Phone.getAccount({
				phoneId : $routeParams.phoneId
			}).then(function(response) {
				ctrl.phone = response.data;
				ctrl.phone.dateOfBirth = new Date(ctrl.phone.dateOfBirth);
			}, function(error) {
				console.log("ERROR PhoneEditController -> getAccount")
			});
		}

		ctrl.updateAccount = function updateAccount(phone) {
			Phone.addAccount(phone).then(function(response) {
				phone = response.data;
				ctrl.success();
			}, function(error) {
				console.log("ERROR PhoneEditController -> updateAccount")
			});
		},

		ctrl.success = function success() {
			var message = '<strong>Account Updated</strong>';

			Notification.success({
				message : message,
				delay : 3000
			});
		},

		ctrl.openDatepicker = function openDatepicker() {
			ctrl.datepicker.opened = true;
		}

	} ]
});

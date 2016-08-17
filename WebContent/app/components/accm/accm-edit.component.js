'use strict';

// Register `accountEdit` component, along with its associated controller and
// template
angular.module('accountEdit').component('accountEdit', {
	templateUrl : 'app/components/accm/accm-edit.template.html',
	controller : [ '$routeParams', 'Account', 'Notification',

	function AccountEditController($routeParams, Account, Notification) {

		var ctrl = this;

		getAccount(this);

		ctrl.account = {};
		ctrl.datepicker = {}

		function getAccount(ctrl) {
			Account.getAccount({
				accountId : $routeParams.accountId
			}).then(function(response) {
				ctrl.account = response.data;
				ctrl.account.dateOfBirth = new Date(ctrl.account.dateOfBirth);
			}, function(error) {
				console.log("ERROR AccountEditController -> getAccount")
			});
		}

		ctrl.updateAccount = function updateAccount(account) {
			Account.updateAccount(account).then(function(response) {
				account = response.data;
				ctrl.success();
			}, function(error) {
				console.log("ERROR AccountEditController -> updateAccount")
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

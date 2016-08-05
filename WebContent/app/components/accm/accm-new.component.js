'use strict';

// Register `accountNew` component, along with its associated controller and
// template
angular.module('accountNew').component('accountNew', {
	templateUrl : 'app/components/accm/accm-new.template.html',
	controller : [ 'Account', '$filter', 'Notification', function AccountNewController(Account, $filter, Notification) {

		var ctrl = this;

		ctrl.account = {};
		ctrl.datepicker = {}

		ctrl.addAccount = function addAccount(account) {
			Account.addAccount(account).then(function(response) {
				account = response.data;
				ctrl.account = {};
				ctrl.reviewForm.$setUntouched();
				ctrl.success();
			}, function(error) {
				console.log("ERROR AccountNewController -> addAccount")
			});
		},

		ctrl.success = function success() {
			var message = '<strong>Account Created</strong>';

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

'use strict';

// Register `phoneDetail` component, along with its associated controller and
// template
angular.module('phoneNew').component(
		'phoneNew',
		{
			templateUrl : 'app/components/accm/accm-new.template.html',
			controller : [ 'Phone', '$filter', 'Notification',
					function PhoneNewController(Phone, $filter, Notification) {

						var ctrl = this;

						ctrl.phone = {};
						ctrl.popup2 = {}

						ctrl.addAccount = function addAccount(phone) {
							Phone.addAccount(phone).then(function(response) {
								phone = response.data;
								ctrl.phone = {};
								ctrl.reviewForm.$setUntouched();
								ctrl.success();
							}, function(error) {
								console.log("ERROR PhoneNewController")
							});
						},

						ctrl.success = function success() {
							var message = '<strong>Account Created</strong>';

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

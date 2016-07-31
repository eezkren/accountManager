'use strict';

// Register `phoneDetail` component, along with its associated controller and
// template
angular.module('phoneNew').component(
		'phoneNew',
		{
			templateUrl : 'app/components/accm/accm-new.template.html',
			controller : [ 'Phone', '$filter', 'Flash',
					function PhoneNewController(Phone, $filter, Flash) {

						var ctrl = this;

						ctrl.phone = {};
						ctrl.popup2 = {}
						ctrl.showSuccess = false;

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
							ctrl.showSuccess = true;
							var message = '<strong>Account Created</strong>';
							Flash.create('success', message);
						},

						ctrl.dismissCallback = function dismissCallback() {
							ctrl.showSuccess = false;
						},

						ctrl.open2 = function open2() {
							ctrl.popup2.opened = true;
						}
					} ]
		});

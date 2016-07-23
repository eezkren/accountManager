'use strict';

// Register `phoneDetail` component, along with its associated controller and
// template
angular.module('phoneEdit').component(
		'phoneEdit',
		{
			templateUrl : 'app/components/accm/accm-edit.template.html',
			controller : [ '$routeParams', 'Phone',
					function PhoneEditController($routeParams, Phone) {

						var self = this;
						self.phone = Phone.get({
							phoneId : $routeParams.phoneId
						}, function(phone) {
						});

						this.addReview = function(Phone) {
							alert(Phone)
						};

					}, function PhoneNewController($routeParams) {

						var self = this;
						self.phone = {};

					} ]
		});

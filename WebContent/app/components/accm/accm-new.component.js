'use strict';

// Register `phoneDetail` component, along with its associated controller and
// template
angular.module('phoneNew').component(
		'phoneNew',
		{
			templateUrl : 'app/components/accm/accm-edit.template.html',
			controller : [ '$routeParams', 'Phone',
					function PhoneNewController($routeParams, Phone) {
						var self = this;
						self.phone = {}
					} ]
		});

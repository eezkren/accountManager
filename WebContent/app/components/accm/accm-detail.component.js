'use strict';

angular.module('phoneDetail').component(
		'phoneDetail',
		{
			templateUrl : 'app/components/accm/accm-detail.template.html',
			controller : [ '$routeParams', 'Phone',
					function PhoneDetailController($routeParams, Phone) {
						getAccount(this);

						function getAccount(ctrl) {
							Phone.getAccount({
								phoneId : $routeParams.phoneId
							}).then(function(response) {
								ctrl.phone = response.data;
							}, function(error) {
								console.log("ERROR PhoneDetailController")
							});
						}

					} ]
		});

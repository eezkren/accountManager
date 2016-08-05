'use strict';

angular.module('accountDetails').component('accountDetails', {
	templateUrl : 'app/components/accm/accm-detail.template.html',
	controller : [ '$routeParams', 'Account', function AccountDetailsController($routeParams, Account) {
		getAccount(this);

		function getAccount(ctrl) {
			Account.getAccount({
				accountId : $routeParams.accountId
			}).then(function(response) {
				ctrl.account = response.data;
			}, function(error) {
				console.log("ERROR AccountDetailsController -> getAccount()")
			});
		}

	} ]
});

'use strict';

// Register `accountList` component, along with its associated controller and
// template
angular.module('accountList').component('accountList', {
	templateUrl : 'app/components/accm/accm-list.template.html',
	controller : [ 'Account', '$scope', function AccountListController(Account, $scope) {

		this.accounts = null;

		getAccounts(this);

		function getAccounts(ctrl) {
			Account.getAccounts().then(function(response) {
				ctrl.accounts = response.data;
			}, function(error) {
				console.log("ERROR AccountListController -> getAccounts")

			});
		}

	} ]
});
'use strict';

angular.module('accountcatApp').config(
		[ '$locationProvider', '$routeProvider', function config($locationProvider, $routeProvider) {
			$locationProvider.hashPrefix('!');

			$routeProvider.when('/accounts', {
				template : '<account-list></account-list>'
			}).when('/accounts/new', {
				template : '<account-new></account-new>'
			}).when('/accounts/:accountId', {
				template : '<account-details></account-details>'
			}).when('/accounts/:accountId/edit', {
				template : '<account-edit></account-edit>'
			}).otherwise('/accounts');
		} ]);
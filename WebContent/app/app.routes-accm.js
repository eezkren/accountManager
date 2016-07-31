'use strict';

angular.module('phonecatApp').config(
		[ '$locationProvider', '$routeProvider',
				function config($locationProvider, $routeProvider) {
					$locationProvider.hashPrefix('!');

					$routeProvider.when('/phones', {
						template : '<phone-list></phone-list>'
					}).when('/phones/new', {
						template : '<phone-new></phone-new>'
					}).when('/phones/:phoneId', {
						template : '<phone-detail></phone-detail>'
					}).when('/phones/:phoneId/edit', {
						template : '<phone-edit></phone-edit>'
					}).otherwise('/phones');
				} ]);
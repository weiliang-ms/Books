var app=angular.module('logApp', []);
app.controller('logController',  function($scope){
	
		$scope.account="";
		$scope.password="";
		$scope.reset=function(){
			$scope.account="";
			$scope.password="";
		}

		$scope.userLog=function(){
			window.location.href="./User.html";
		}
		$scope.managerLog=function(){
			if ($scope.account==""||$scope.account=="") {
				alert("账号密码不能为空！");
			} else if($scope.account!="sys"||$scope.account!="sys"){
				alert("账号密码错误！");
			} else {
				alert("登陆成功！");
				window.location.href="./Manager.html";
			}	
		}

});
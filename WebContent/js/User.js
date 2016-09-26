var app=angular.module('selectApp', []);
 app.controller('selectCtr',  function($scope,$http){
	 
	 
    $http.post('http://localhost:8080/BookManageSystem/run').success(function(data)
        {
            $scope.names=data;
        });

        $scope.information="输入你要查询的内容";
        $scope.select=function(){
            // $scope.names="";
            var selectN = $scope.information;
        var postData = "?name="+selectN+"";
        var url = "http://localhost:8080/BookManageSystem/select" + postData;
        $http.post(url).success(function(data)
        {
        	
            if (data=="NOTHING") {
                alert("无相关书籍,请更改您的输入内容！");
            } else {
                alert("查询完成！");
                $scope.names=data;
            }       
        });

        };
        $scope.refresh=function(){
             $http.post('http://localhost:8080/BookManageSystem/run').success(function(data)
        {
            $scope.names=data;
        });
        };
    
});
 app.controller('borrowCtr', function($scope,$http){
        
        $scope.borrow=function(name,btype){
        
        var postData = "?bookName="+name+"&"+"type="+btype+"";
        var url = "http://localhost:8080/BookManageSystem/br" + postData;

            $http.post(url).success(function(data)
        {
            alert(data);
        });

        }

    });
var app=angular.module('selectApp', []);
 app.controller('selectCtr',  function($scope,$http){
	 // 添加书籍的信息
	 $scope.bookName="";
	 $scope.bookAuthor="";
	 $scope.bookType="";

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
        
         $scope.addBook=function(){
        	 alert("添加新书");
         	var postData = "?name="+$scope.bookName+"&author="+$scope.bookAuthor+"&type="+$scope.bookType+"";
         	var url = "http://localhost:8080/BookManageSystem/add" + postData;
            $http.post(url).success(function(data)
        {
            
        	if (data=="success") {
				alert("添加成功");
				$http.post('http://localhost:8080/BookManageSystem/run').success(function(data)
				        {
				            $scope.names=data;
				        });
				
			}else{
				
				alert("该书籍已经存在");
			}
        	
  
        });
        };


        /*获得修改权限*/ 
        var judgeState = false;
        $scope.updateName="获得名字修改";
        $scope.updateAuthor="获得作者修改";
        $scope.updateType="获得类型修改";
        $scope.objectName;
        $scope.toggleTooltip = function(name,author,type){
        	$scope.updateName = name;
        	$scope.updateAuthor = author;
        	$scope.updateType = type;
        	$scope.objectName = $scope.updateName;
        		document.getElementById("state1").disabled="";
        		document.getElementById("state2").disabled="";
        		document.getElementById("state3").disabled="";
        		document.getElementById("state4").disabled="";
    	}


        /*执行修改权限*/
    	$scope.update=function(){
	    		document.getElementById("state1").disabled="disabled";
				document.getElementById("state2").disabled="disabled";
				document.getElementById("state3").disabled="disabled";
				document.getElementById("state4").disabled="disabled";
				var param= "?name="+$scope.updateName+"&author="+$scope.updateAuthor+"&type="+$scope.updateType+"&nameObject="+$scope.objectName+"";
	         	var urlDelete = "http://localhost:8080/BookManageSystem/update" + param;
	            $http.post(urlDelete).success(function(datas)
	        {
	            	alert("修改成功");
	        });
	         	
    	}

});


/*删除*/ 
 app.controller('deleteCtr', function($scope,$http){
        
        $scope.deleteBook=function(name){
        
        var postData = "?bookName="+name+"";
        var url = "http://localhost:8080/BookManageSystem/delete" + postData;

            $http.post(url).success(function(data)
        {
            alert(data);
             window.location.href="../html/Manager.html";
          
        });

            
        }
        
        
    	
    });
 
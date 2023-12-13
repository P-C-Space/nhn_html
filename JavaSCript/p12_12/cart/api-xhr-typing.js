const SERVER_URL="http://133.186.241.167:8100";

window.addEventListener("DOMContentLoaded",function(){
    'use strict'
    const loginForm = this.document.getElementById("login-form");

    const validateForm=function(form){
        form['userId'].value
        form['userPassword'].value

        if(form['userId'].value.trim()==' '){
            alert("empty user_id");
            form['userId'].focus();
            return false;
        } else if(form['userPassword'].value.trim()==' '){
            this.alert("empty user-password");
            form['userPassword'].focus();
            return false;
        }

        return true;
    }



    loginForm.addEventListener('submit',function(event){
        event.preventDefault();
        if(!validateForm(event.target)){
            return ;
        }


        const loginSuccess = function(user){
            console.log("userId : " + user.userId);
            console.log("userPassword : " + user.userPassword);
            const userId = user.userId;
            const password = user.password

            // login ui 구현
            const loginWrapper = document.getElementById("login-wrapper");
            loginWrapper.setAttribute("style","display:none;");
            const loginSuccess = document.getElementById("login-success");
            loginSuccess.setAttribute("style","display:block");
            
            const loginUserId = document.getElementById("login-userId");
            const loginUserName = document.getElementById("login-userName");
            const loginCartId = document.getElementById("login-cartId");

            loginUserId.innerText=user.userId;
            loginUserName.innerText=user.userName;
            loginCartId.innerText=user.cartId;



            getCartItems(user.userId,user.cartId,function(items){
                // 장바구니 ui 구현
                getCartItems(user.userId, user.cartId, function(items){
                    const cartTable = document.getElementById("cart-table");
                    const body = cartTable.getElementsByTagName("tbody")[0];
                    const intl = new Intl.NumberFormat();
    
                    for (const item of items) {
                        const tr = document.createElement("tr");
                        const td1 = document.createElement("td");
                        const td2 = document.createElement("td");
                        const td3 = document.createElement("td");
                        const td4 = document.createElement("td");
                        const td5 = document.createElement("td");
                        td1.innerText=item.productId;
                        td2.innerText=item.name;
                        td3.innerText=intl.format(item.price);
                        td4.innerText=intl.format(item.amount);
                        td5.innerText= intl.format(item.totalPrice);
                        tr.append(td1,td2,td3,td4,td5);
                        body.append(tr);
                    }
                });
            });
        }

        doLogin(loginForm["userId"].value,loginForm["userPassword"].value,loginSuccess);
        
    });



});

function getCartItems(userId, cartId, viewCartItems){
    const xhr = new XMLHttpRequest();
    const url = SERVER_URL + "/api/nhnmart/shopping-cart/"+cartId;
    console.log(url);

    xhr.addEventListener("load",function(){
        if(this.status === 200){
            console.log(this.response);
            viewCartItems(this.response);
        }
    });

    xhr.open("GET",url);
    xhr.setRequestHeader("content-type","application/json");
    xhr.responseType="json";
    xhr.setRequestHeader("X-USER-ID",userId);
    xhr.send('')
}

function doLogin(paramUserId,paramUserPassword, loginSuccess){
    console.log("call dologin!")
    const xhr = new XMLHttpRequest();
    const url = SERVER_URL + "/api/users/login"
    console.log(url)

    const data = {
        userId : paramUserId,
        userPassword : paramUserPassword
    }

    xhr.addEventListener('load',function(){
        if(this.status === 200){
            console.log(this.response);
            loginSuccess(this.response);
            return this.response;
        }
    });

    xhr.open("POST",url);
    xhr.setRequestHeader("content-type","application/json");
    xhr.responseType="json";
    xhr.send(JSON.stringify(data));
}
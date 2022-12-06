//다크모드
/*asideRight_Bf*/
const screenMode = document.querySelector("#screenMode");
const asideWriteBf = document.querySelector("#asideWriteBf");
const body = document.querySelector("body");
const asideLoginBf = document.querySelector("#asideLoginBf");
const MyWrapBf = document.querySelector("#MyWrapBf");
const mainRightAd = document.querySelector("#mainRightAd");
/*header*/
const header = document.querySelector("#header");


screenMode.addEventListener('click', (e)=>{
    if (screenMode.hasAttribute('class','dark')) {
        e.currentTarget.removeAttribute('class','dark');
        asideWriteBf.removeAttribute('class', 'dark');
        body.removeAttribute('class', 'dark');
        asideLoginBf.removeAttribute('class', 'dark');
        MyWrapBf.removeAttribute('class', 'dark');
        mainRightAd.removeAttribute('class', 'dark');
        /*header*/
        header.removeAttribute('class', 'dark');
        
    }
    else {
        e.currentTarget.setAttribute('class', 'dark');
        asideWriteBf.setAttribute('class', 'dark');
        body.setAttribute('class', 'dark');
        asideLoginBf.setAttribute('class', 'dark');
        MyWrapBf.setAttribute('class', 'dark');
        mainRightAd.setAttribute('class', 'dark');
        /*header*/
        header.setAttribute('class', 'dark');

    }

})
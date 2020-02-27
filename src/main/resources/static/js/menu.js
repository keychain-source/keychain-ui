
var sse60 = function () {
    // For the six color variables below, you can set one color for all menu tabs, or specify a list of colors for each menu tab.
    var fontColors = ["#FFFFFF"];
    var fontHoverColors = ["#FFFFFF"];

    var borderColors = ["#CC8300", "#9E0E87", "#D12E2E", "#0D507A", "#8B6846", "#74A824","#CC8300", "#9E0E87", "#D12E2E", "#0D507A"];
    var borderHoverColors = ["black"];

    var bgColors = ["#8B6846", "#74A824", "#CC8300", "#0D507A", "#CC33CC", "#D12E2E","#8B6846", "#74A824", "#CC8300", "#0D507A"];
    var bgHoverColors = ["#336699", "#336699", "#336699", "#336699", "#336699", "#336699","#336699", "#336699", "#336699"];

    var a;
    return {
        initMenu: function () {
            var m = document.getElementById('sses60');
            if (!m) return;
            m.style.width = m.getElementsByTagName("ul")[0].offsetWidth + 4 + "px";
            a = m.getElementsByTagName("a");
            var url = document.location.href.toLowerCase();
            var k = -1;
            var nLength = -1;

            //find current index: k
            for (i = 0; i < a.length; i++) {
                if (url.indexOf(a[i].href.toLowerCase()) != -1 && a[i].href.length > nLength) {
                    k = i;
                    nLength = a[i].href.length;
                }
                a[i].setAttribute("i", i);
            }
            if (k == -1 && /:\/\/(?:www\.)?[^.\/]+?\.[^.\/]+\/?$/.test) {
                for (var i = 0; i < a.length; i++) {
                    if (a[i].getAttribute("maptopuredomain") == "true") {
                        k = i;
                        break;
                    }
                }
                if (k == -1 && a[0].getAttribute("maptopuredomain") != "false")
                    k = 0;
            }

            for (i = 0; i < a.length; i++) {
                if (i != k) {
                    a[i].onmouseover = function () {
                        sse60.setColors(this.getAttribute("i"), true);
                    }
                    a[i].onmouseout = function () {
                        sse60.setColors(this.getAttribute("i"), false);
                    };
                }
                if (i == k)
                    sse60.setColors(i, true);
                else
                    sse60.setColors(i, false);
            }
        },

        setColors: function (i, hover) {
            if (hover) {
                a[i].style.color = fontHoverColors.length > 1 ? fontHoverColors[i] : fontHoverColors[0];
                a[i].style.borderTop = "solid 4px " + (borderHoverColors.length > 1 ? borderHoverColors[i] : borderHoverColors[0]);
                a[i].style.background = bgHoverColors.length > 1 ? bgHoverColors[i] : bgHoverColors[0];
            }
            else {
                a[i].style.color = fontColors.length > 1 ? fontColors[i] : fontColors[0];
                a[i].style.borderTop = "solid 4px " + (borderColors.length > 1 ? borderColors[i] : borderColors[0]);
                a[i].style.background = bgColors.length > 1 ? bgColors[i] : bgColors[0];
            }
        }
    };
} ();

if (window.addEventListener) {
    window.addEventListener("load", sse60.initMenu, false);
}
else if (window.attachEvent) {
    window.attachEvent("onload", sse60.initMenu);
}
else {
    window["onload"] = sse60.initMenu;
}




var options = function () {
    // For the six color variables below, you can set one color for all menu tabs, or specify a list of colors for each menu tab.
    var fontColors = ["#FFFFFF"];
    var fontHoverColors = ["#FFFFFF"];

    var borderColors = ["#CC8300", "#9E0E87", "#D12E2E", "#0D507A", "#8B6846", "#74A824","#CC8300", "#9E0E87","#CC8300", "#9E0E87", "#D12E2E", "#0D507A"];
    var borderHoverColors = ["black"];

    var bgColors = ["#8B6846", "#74A824", "#CC8300", "#0D507A", "#CC33CC", "#D12E2E","#8B6846", "#74A824","#8B6846", "#74A824", "#CC8300", "#0D507A"];
    var bgHoverColors = ["#336699", "#336699", "#336699", "#336699", "#336699", "#336699","#336699", "#336699","#336699", "#336699", "#336699", "#336699"];

    var a;
    return {
        initMenu: function () {
            var m = document.getElementById('optionslist');
            if (!m) return;
            m.style.width = m.getElementsByTagName("ul")[0].offsetWidth + 1 + "px";
            a = m.getElementsByTagName("a");
            var url = document.location.href.toLowerCase();
            var k = -1;
            var nLength = -1;

            //find current index: k
            for (i = 0; i < a.length; i++) {
                if (url.indexOf(a[i].href.toLowerCase()) != -1 && a[i].href.length > nLength) {
                    k = i;
                    nLength = a[i].href.length;
                }
                a[i].setAttribute("i", i);
            }
            if (k == -1 && /:\/\/(?:www\.)?[^.\/]+?\.[^.\/]+\/?$/.test) {
                for (var i = 0; i < a.length; i++) {
                    if (a[i].getAttribute("maptopuredomain") == "true") {
                        k = i;
                        break;
                    }
                }
                if (k == -1 && a[0].getAttribute("maptopuredomain") != "false")
                    k = 0;
            }

            for (i = 0; i < a.length; i++) {
                if (i != k) {
                    a[i].onmouseover = function () {
                        options.setColors(this.getAttribute("i"), true);
                    }
                    a[i].onmouseout = function () {
                        options.setColors(this.getAttribute("i"), false);
                    };
                }
                if (i == k)
                    options.setColors(i, true);
                else
                    options.setColors(i, false);
            }
        },

        setColors: function (i, hover) {
            if (hover) {
                a[i].style.color = fontHoverColors.length > 1 ? fontHoverColors[i] : fontHoverColors[0];
                a[i].style.borderTop = "solid 4px " + (borderHoverColors.length > 1 ? borderHoverColors[i] : borderHoverColors[0]);
                a[i].style.background = bgHoverColors.length > 1 ? bgHoverColors[i] : bgHoverColors[0];
            }
            else {
                a[i].style.color = fontColors.length > 1 ? fontColors[i] : fontColors[0];
                a[i].style.borderTop = "solid 4px " + (borderColors.length > 1 ? borderColors[i] : borderColors[0]);
                a[i].style.background = bgColors.length > 1 ? bgColors[i] : bgColors[0];
            }
        }
    };
} ();

if (window.addEventListener) {
    window.addEventListener("load", options.initMenu, false);
}
else if (window.attachEvent) {
    window.attachEvent("onload", options.initMenu);
}
else {
    window["onload"] = options.initMenu;
}




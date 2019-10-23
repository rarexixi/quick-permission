const commonFun = {
    getParam: function (name, path = '') {
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        let r = (path && path != '' ? path : window.location.search).substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return '';
    },
    setCookie: function (name, value, domain = '', path = '/', expires = 3600 * 1000) {
        document.cookie = `${name}=${value};domain=${domain};path=${path};expires=${Date.now() + expires}`;
    },
    getCookie: function (name) {
        let arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        return (arr = document.cookie.match(reg)) ? unescape(arr[2]) : null;
    },
    delCookie: function (name) {
        document.cookie = `${name}=null;expires=${Date.now() - 1}`;
    },
    setVueModel: function (vueObj, targetModel, val) {
        let index = targetModel.indexOf('.');
        if (index === -1) {
            Vue.set(vueObj, targetModel, val);
            return;
        }
        Vue.set(vueObj[targetModel.substr(0, index)], targetModel.substr(index + 1), val);
    },
    formatDateTime: function (timestamp, fmt) {
        if (typeof (timestamp) == 'number' && timestamp <= 0) {
            return '-';
        }
        let date = new Date(timestamp);
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        let o = {
            'M+': date.getMonth() + 1,
            'd+': date.getDate(),
            'h+': date.getHours(),
            'm+': date.getMinutes(),
            's+': date.getSeconds()
        };
        for (let k in o) {
            if (new RegExp(`(${k})`).test(fmt)) {
                let str = o[k] + '';
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : ('00' + str).substr(str.length));
            }
        }
        return fmt;
    },
    getUUID: function () {
        return 'xxxxxxxxxxxxxxxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            let r = Math.random() * 16 | 0,
                v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    },
    getUUIDWidthHyphen: function () {
        return 'xxxxxxxx-xxxx-xxxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            let r = Math.random() * 16 | 0,
                v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }
};

/*vue 格式化日期函数*/
Vue.filter('formatDate', timestamp => commonFun.formatDateTime(timestamp, 'yyyy-MM-dd'));
/*vue 格式化时间函数*/
Vue.filter('formatTime', timestamp => commonFun.formatDateTime(timestamp, 'hh:mm:ss'));
/*vue 格式化日期时间函数*/
Vue.filter('formatDateTime', timestamp => commonFun.formatDateTime(timestamp, 'yyyy-MM-dd hh:mm:ss'));

function notifyResponseMsg(vue, title, response) {
    title = response.msg || title;
    let msg = '';
    if (response.extData) {
        if (typeof response.extData == "string") {
            msg += '<br/>' + response.extData;
        } else {
            response.extData.forEach((item, index) => {
                msg += '<br/>' + item;
            });
        }
    }
    vue.$notify({
        dangerouslyUseHTMLString: true,
        title: title,
        message: msg,
        type: 'error'
    });
}

Vue.prototype.ajaxPost = function (url, params, failMsg, successCallback, failCallback, errorCallback) {

    let self = this;
    axios.post(appConfig.baseApiPath + url, params).then(res => {
        let response = res.data;
        if (response.success == true) {
            if (successCallback) successCallback(response);
        } else {
            notifyResponseMsg(self, failMsg, response);
            if (failCallback) failCallback();
        }
    }).catch(error => {
        self.$notify({
            message: error,
            type: 'error'
        });
        if (errorCallback) errorCallback();
    });
};

Vue.prototype.ajaxGet = function (url, params, failMsg, successCallback, failCallback, errorCallback) {

    let self = this;
    axios.get(appConfig.baseApiPath + url, {
        params: params
    }).then(res => {
        let response = res.data;
        if (response.success == true) {
            if (successCallback) successCallback(response);
        } else {
            notifyResponseMsg(self, failMsg, response);
            if (failCallback) failCallback();
        }
    }).catch(error => {
        self.$notify({
            message: error,
            type: 'error'
        });
        if (errorCallback) errorCallback();
    });
};

Vue.prototype.confirmPost = function (confirmMsg, url, params, successMsg, failMsg, successCallback, failCallback, cancelCallback) {
    let self = this;
    self.$confirm(confirmMsg, '', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        self.ajaxPost(url, params, failMsg, response => {
            self.$notify({
                type: 'success',
                message: successMsg
            });
            if (successCallback) successCallback(response);
        }, () => {
            if (failCallback) failCallback(response);
        });
    }, () => {
        if (cancelCallback) cancelCallback();
    });
};

Vue.prototype.confirmGet = function (confirmMsg, url, params, successMsg, failMsg, successCallback, failCallback) {
    let self = this;
    self.$confirm(confirmMsg, '', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        self.ajaxGet(url, params, failMsg, response => {
            self.$notify({
                type: 'success',
                message: successMsg
            });
            if (successCallback) successCallback(response);
        }, () => {
            if (failCallback) failCallback(response);
        });
    }, () => {
    });
};

Vue.prototype.execPost = function (url, params, successMsg, failMsg, successCallback) {
    let self = this;
    self.ajaxPost(url, params, failMsg, response => {
        self.$notify({
            type: 'success',
            message: successMsg
        });
        if (successCallback) successCallback(response);
    });
};

Vue.prototype.validatePermissions = function (permissions, viewPermission, callback) {

    let self = this;
    let permissionList = [];
    for (let permission in permissions) {
        permissionList.push(permission);
    }
    axios.post(appConfig.validatePermissionsUrl, permissionList).then(res => {
        let response = res.data;
        if (response.success == true) {
            let permissionResult = response.data;
            for (let permission in permissionResult) {
                permissions[permission] = permissionResult[permission] || false;
            }
            if (viewPermission) {
                if (permissions[viewPermission]) {
                    if (callback) callback();
                } else {
                    location.href = appConfig.noPermissionUrl;
                }
            } else {
                if (callback) callback();
            }
        } else if (response.code == 100212) {
            // 未登录或token过期
            if (window == parent) {
                location.href = appConfig.loginUrl;
            } else if (parent && parent.app && parent.app.login) {
                parent.app.login();
            }
        } else {
            notifyResponseMsg(self, "验证权限失败", response);
        }
    }).catch(error => {
        self.$notify({
            message: error,
            type: 'error'
        });
    });
};

Vue.prototype.login = function () {
    if (window == parent) {
        location.href = appConfig.loginUrl;
    } else if (parent && parent.app && parent.app.login) {
        parent.app.login();
    }
};

Vue.prototype.openLink = function (url, id, name) {
    if (url) {
        if (parent != window && parent.app && parent.app.openLink) {
            parent.app.openLink(url, id, name);
        } else {
            window.open(url);
        }
    }
};
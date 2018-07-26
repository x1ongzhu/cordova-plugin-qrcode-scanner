var exec = require("cordova/exec");

exports.start = function(success, options) {
    exec(
        function(message) {
            if (typeof success === "function") {
                success(message);
            }
            console.log(message);
        },
        function(message) {
            console.log(message);
        },
        "qrCodeScanner",
        "start",
        [options]
    );
};

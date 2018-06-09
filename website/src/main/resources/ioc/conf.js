var ioc = {
    config : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			ignoreResourceNotFound : true,
			utf8 : false,
			paths : ['conf']
		}
	},
    conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            ignoreResourceNotFound : true,
            utf8 : false,
            paths : ['conf']
        }
    }
};

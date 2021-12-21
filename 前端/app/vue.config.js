module.exports={
    //publicPath: process.env.NODE_ENV === 'production' ? '/static/' : './',
    publicPath: './',
    // 输出文件目录   文件夹名
    // outputDir: 'dists',
    // // 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。    资源放的目录
    // assetsDir: "public",
    // // 指定生成的 index.html 的输出路径 (相对于 outputDir)。也可以是一个绝对路径    index的路劲和名字
    // indexPath: './m_index.html',
    // // eslint-loader 是否在保存的时候检查
    lintOnSave: true,
    // runtimeCompiler: true, // 运行时版本是否需要编译
    transpileDependencies: [], // 默认babel-loader忽略mode_modules，这里可增加例外的依赖包名
    productionSourceMap: false, // 是否在构建生产包时生成 sourceMap 文件，false将提高构建速度
    devServer:{
        overlay:{
            warning:true,
            errors:true
        },
        port:8321,
        // proxy: {
        //     '/api': {
        //         target: 'https://wantyou.csust-iotlab.com', // 要代理的域名
        //         changeOrigin: true,//允许跨域
        //         pathRewrite: {
        //             '^/api': '' // 这个是定义要访问的路径，名字随便写
        //         }
        //     }
        // }
    },
    configureWebpack:{
        resolve:{
            alias:{
                'assets':'@/assets',
                'components':'@/components',
                'api':'@/api',
                'router':'@/router',
                'storage':'@/index',
                'store':'@/index',
                'views':'@/views'
            }
        }
    },
    chainWebpack:config=>{
        config.resolve.symlinks(true);
    }
}

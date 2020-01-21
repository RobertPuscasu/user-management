const path = require('path');
const webpack = require('webpack');
/* eslint no-use-before-define: 0 */
module.exports = {
  mode: 'development',
  bail: false,
  output: {
    chunkFilename: '[id].chunk.js',
    filename: '[name].bundle.js',
    path: path.join(process.cwd(), 'build'),
    pathinfo: false,
    publicPath: '/',
  },
  plugins: [new webpack.HotModuleReplacementPlugin(), new webpack.NoEmitOnErrorsPlugin()],
  performance: {
    hints: false,
  },
};

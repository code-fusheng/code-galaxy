import tinify
import os

tinify.key = 'MryQMvp8f4cj0TcF6kXXJbkGrDJBMbnf'
png_path = r"/Users/zhanghao/Downloads/归档 2/gxqyrd/img"
for i in os.listdir(png_path):
    if os.path.splitext(i)[1] == ".png":
        print("开始压缩图片: ", i)
        source = tinify.from_file(os.path.join(png_path, i))
        opt = png_path + '-optimize'
    if not os.path.exists(opt):
        os.makedirs(opt)
    source.to_file(os.path.join(opt, i))
    print("压缩完成: ", i)
print('over')

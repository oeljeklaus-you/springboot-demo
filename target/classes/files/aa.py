#encoding=utf-8
import urllib.request
import re
import time
#from bs4 import BeautifulSoup

opener = urllib.request.build_opener()
opener.addheaders = [('User-agent',
                      'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36')]

def get_article_url(page,name):
    endurl = "/"+name+"/article/details/........"
    print(name)
    p = re.compile(endurl)
    url = "http://blog.csdn.net/"+name+"/article/list/"+str(page)
    # 使用build_opener()是为了让python程序模仿浏览器进行访问
    html = opener.open(url).read().decode('utf-8')
    allfinds = p.findall(html)
    return allfinds
    #print('allfinds',allfinds)

def start_do(allfinds):
    urlBase = "http://blog.csdn.net"  # 需要将网址合并的部分
    # 页面中的网址有重复的，需要使用set进行去重复
    mypages = list(set(allfinds))
    for i in range(len(mypages)):
        mypages[i] = urlBase + mypages[i]
    # print('要刷的网页有：')
    # for index, page in enumerate(mypages):
    #     print(str(index), page)

    # 设置每个网页要刷的次数
    brushNum = 1

    # 所有的页面都刷
    print('下面开始刷了哦：')
    for index, page in enumerate(mypages):
        for j in range(brushNum):
            try:
                opener.open(page).read().decode('utf-8')
                # 使用BeautifulSoup解析每篇博客的标题
                #soup = BeautifulSoup(pageContent)
                #blogTitle = str(soup.title.string)
                #blogTitle = blogTitle[0:blogTitle.find('-')]
                #print(str(j), blogTitle)
            except urllib.error.HTTPError:
                print('urllib.error.HTTPError')
                time.sleep(3)  # 出现错误，停几秒先
                #opener.open(page).read().decode('utf-8')
            except urllib.error.URLError:
                print('urllib.error.URLError')
                time.sleep(3)  # 出现错误，停几秒先
                time.sleep(0.5)  # 正常停顿，以免服务器拒绝访问
                #opener.open(page).read().decode('utf-8')

def main():
    #name = "blue_zeno"
    name = "oeljeklaus"
    #name = "qq_34292044"
    for page in range(1,11):
        print("************第"+str(page)+"页*************")
        endurl = get_article_url(page,name)
        try:
           start_do(endurl)
        except:
           main()
    print("开始休息")
    time.sleep(40)

if __name__ == '__main__':
    while 1:
        main()


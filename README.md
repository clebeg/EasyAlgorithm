# EasyAlgorithm
implement all data structure and algorithm by java, add use best way!      
用Java轻松掌握所有算法与数据结构追求最高效的方式实现     
**Author:** clebeg

## 一、数据结构(data structure)
package name: io.github.clebeg.model

## 二、算法
### 2.1 排序算法(sort algorithm)
**算法效率:**      
![排序算法效率](https://img-blog.csdn.net/20180807094112221?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTYyNjAw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)    
package name: io.github.clebeg.algo.sort     

**实际测试:**    
[io.github.clebeg.algo.sort.SortAlgoTest](https://github.com/clebeg/EasyAlgorithm/blob/master/src/test/java/io/github/clebeg/algo/sort/SortAlgoTest.java)

```shell script
io.github.clebeg.algo.sort.InsertSort
InsertSort{costTime=64398(ms), swapTimes=0, compareTimes=1415881218}
io.github.clebeg.algo.sort.ShellSort
ShellSort{costTime=49(ms), swapTimes=0, compareTimes=3002422}
io.github.clebeg.algo.sort.QuickSort
QuickSort{costTime=106(ms), swapTimes=132690, compareTimes=4315318}
```
**已经实现的算法**
#### 2.1.1 交换排序算法
- [x] 快速排序：[io.github.clebeg.algo.sort.QuickSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/QuickSort.java)

#### 2.1.2 插入排序算法
- [x] 插入排序：[io.github.clebeg.algo.sort.InsertSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/InsertSort.java)
- [x] 希尔排序：[io.github.clebeg.algo.sort.ShellSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/ShellSort.java)

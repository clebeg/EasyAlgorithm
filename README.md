[TOC]
# EasyAlgorithm
implement all data structure and algorithm by java, add use best way implement!      
尝试用 Java 轻松掌握所有算法与数据结构，并且追求最高效的方式实现

## 一、数据结构(data structure)
package name: io.github.clebeg.model
### 1.1 数组表示树
二叉堆、优先队列、并查集 都是用数组表示的树型结构）
- [x] 堆-二叉堆：[io.github.clebeg.algo.model.BinaryHeap](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/model/BinaryHeap.java)
- [x] 并查集：[io.github.clebeg.algo.model.UnionFind](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/model/UnionFind.java)

### 1.2 树基本表示
二叉查找树
- [x] 红黑树：[io.github.clebeg.algo.model.tree.RBTree](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/model/tree/RBTree.java)

### 1.3 图基本表示
图有两种基本的表示方法，邻接矩阵和邻接表 此处采用的是邻接表，但是也记录指向每个顶点的边
邻接表表示法：[io.github.clebeg.algo.model.graph.ListGraph](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/model/graph/ListGraph.java)
- [x] 广度优先搜索: 具体见 ListGraph 的 bfs 方法
- [x] 深度优先搜索: 具体见 ListGraph 的 dfs 方法
- [x] 最小生成树Prim算法: 具体见 ListGraph 的 prim 方法
- [ ] 最小生成树Kruskal算法: 具体见 ListGraph 的 kruskal 方法

## 二、算法
### 2.1 排序算法(sort algorithm)
**算法效率:**      
![7大基于比较的排序算法效率](https://segmentfault.com/img/bVbCXi7)    
package name: io.github.clebeg.algo.sort 

**实际测试:**    
[io.github.clebeg.algo.sort.SortAlgoTest](https://github.com/clebeg/EasyAlgorithm/blob/master/src/test/java/io/github/clebeg/algo/sort/SortAlgoTest.java)

```shell script
io.github.clebeg.algo.sort.MergeSort
MergeSort{costTime=71(ms), swapTimes=0, compareTimes=1536633}
io.github.clebeg.algo.sort.QuickSort
QuickSort{costTime=49(ms), swapTimes=65113, compareTimes=2158939}
io.github.clebeg.algo.sort.InsertSort
InsertSort{costTime=8209(ms), swapTimes=0, compareTimes=-1796649581}
io.github.clebeg.algo.sort.ShellSort
ShellSort{costTime=53(ms), swapTimes=0, compareTimes=4400494}
io.github.clebeg.algo.sort.SelectSort
SelectSort{costTime=15995(ms), swapTimes=99999, compareTimes=704982704}
io.github.clebeg.algo.sort.BubbleSort
BubbleSort{costTime=49027(ms), swapTimes=-1796749567, compareTimes=704772374}
io.github.clebeg.algo.sort.HeapSort
HeapSort{costTime=30(ms), swapTimes=1574810, compareTimes=3019230}
```
**已经实现的算法**

- [x] 归并排序：[io.github.clebeg.algo.sort.MergeSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/MergeSort.java)
- [x] 快速排序：[io.github.clebeg.algo.sort.QuickSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/QuickSort.java)
- [x] 插入排序：[io.github.clebeg.algo.sort.InsertSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/InsertSort.java)
- [x] 希尔排序：[io.github.clebeg.algo.sort.ShellSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/ShellSort.java)
- [x] 选择排序：[io.github.clebeg.algo.sort.SelectSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/SelectSort.java)
- [x] 堆排序：[io.github.clebeg.algo.sort.HeapSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/HeapSort.java)
- [x] 选择排序：[io.github.clebeg.algo.sort.BubbleSort](https://github.com/clebeg/EasyAlgorithm/blob/master/src/main/java/io/github/clebeg/algo/sort/BubbleSort.java)

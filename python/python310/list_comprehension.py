# 列表推导式


# 案例1：创建一个列表，其元素为原列表元素的平方
original_list = [1, 2, 3, 4, 5]
squared_list = [x * x for x in original_list]
print(squared_list)  # 输出：[1, 4, 9, 16, 25]

# 案例2：创建列表，只包含原列表中的偶数
original_list = [1, 2, 3, 4, 5, 6, 7, 8, 9]
even_list = [x for x in original_list if x % 2 == 0]
print(even_list)  # 输出：[2, 4, 6,8]

# 案例3：创建一个列表，将原列表中的偶数乘以3
original_list = [1, 2, 3, 4, 5, 6, 7, 8, 9]
multiplied_even_list = [x * 3 for x in original_list if x % 2 == 0]
print(multiplied_even_list)  # 输出：[6, 12, 18, 24]
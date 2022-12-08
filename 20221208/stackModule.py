#!/usr/bin/env python
# coding: utf-8

# In[1]:


class Stack:
    # Stack 클래스가 생성될 때 스택의 크기를 넘겨받으면 넘겨받은 크기만큼의 기억공간을 가지는 스택을 생성하고 스택의 크기를
    # 넘겨받지 않으면 5개의 데이터를 저장할 수 있는 스택을 만든다. => 디폴트 인수를 사용한다.
    def __init__(self, size=5):
        # print('스택의 크기: {}'.format(size))
        # 생성자 함수에서 스택으로 사용할 빈 리스트를 만든다.
        self.stack = [] # stack => 빈 리스트 => 데이터는 append() 메소드로 추가한다.
        self.size = size # 스택의 크기
        # top, SP(Stack Pointer) => 스택에 몇 개의 데이터가 저장되어있나 기억한다.
        # 데이터가 입력되면 1증가, 데이터가 출력되면 2감소 => 스택의 크기와 비교해서 overflow, underflow를 처리한다.
        self.top = 0
        
    # push => 입력
    def push(self, data):
        if data not in self.stack: # 스택에 저장하려는 데이터가 스택에 존재하지 않는가?
            # 스택에 저장하려는 데이터가 스택에 존재하지 않기 때문에 저장하기 전에 overflow인가 검사한다.
            # 스택의 크기(self.size)가 5일때 스택으로 사용할 리스트의 인덱스(self.top)는 0, 1, 2, 3, 4만 사용할 수 있다.
            if self.size > self.top:
                # overflow가 발생되지 않았으므로 스택에 데이터를 저장한다.
                self.stack.append(data)
                # 스택에 데이터를 추가했으므로 top을 1증가시킨다.
                self.top += 1
            else:
                # overflow가 발생되면 스택이 가득찼다는 메시지를 출력한다.
                print('overflow 발생... 스택이 가득차서 {}를(을) 저장할 수 없습니다.'.format(data))
            # ===== if
        else:
            # 스택에 저장하려는 데이터가 스택에 존재하기 때문에 중복된 데이터를 메시지를 출력한다.
            print('{}는(은) 중복된 데이터 입니다.'.format(data))
        # ===== if
        # 스택에 저장된 데이터를 출력하는 함수(view)를 실행한다.
        # 현재 클래스 내부에 다른 함수를 실행하려는 경우 앞에 'self'를 붙여서 실행해야 한다.
        self.view()
    
    # view => 보기, 스택에 저장된 데이터를 출력한다.
    def view(self):
        print('스택에 저장된 데이터 => ', end='')
        # underflow 인가 검사한다.
        if self.top <= 0:
            # 스택에 저장된 데이터가 없으므로 없다고 출력한다.
            print('없음')
        else:
            # 스택에 저장된 데이터의 개수만큼 반복하여 스택에 저장된 데이터를 출력한다.
            # for i in range(len(self.stack)):
            # for i in range(self.top):
                # print(self.stack[i], end=' ')
            for s in self.stack:
                print(s, end=' ')
            print()
        # ===== if
    
    # pop => 출력
    def pop(self):
        # underflow 인가 검사한다.
        if self.top <= 0:
            print('스택에 저장된 데이터가 없습니다.')
        else:
            # 파이썬 리스트 메소드 중에서 pop() 메소드를 사용해서 스택에 저장된 데이터를 얻어온 후 리스트에서 제거한다.
            data = self.stack.pop()
            # 스택에 저장된 데이터가 출력되었으므로 top을 1감소 시킨다.
            self.top -= 1
            print('pop 데이터: {}'.format(data))
            self.view()


# In[2]:


if __name__ == '__main__':
    stack = Stack()
    stack.view()
    stack.pop()
    stack.push(111)
    stack.push(111)
    stack.push(3.141592)
    stack.push('수요일')
    stack.push(True)
    stack.push(555)
    stack.push(777)
    print('=' * 80)
    stack.view()
    print('=' * 80)
    stack.pop()
    stack.pop()
    stack.pop()
    stack.pop()
    stack.pop()
    stack.pop()


# In[ ]:





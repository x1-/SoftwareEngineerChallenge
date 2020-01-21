# Answer for 1.Coding task

`Queue` と `Queue2` の 2パターンを実装しています.  
Implemented as `Queue` and `Queue2`.  

### 理由

`Queue` においては, TypeTag を `[T]` から `[+T]` へ変更しています.  
加えて, `Queue` を共変にしたため,  `enQueue` に TypeTag を追加しています.  

よって厳密に言えばAPIのインターフェイスが変わってしまうので, 別の解答である `Queue2` を用意しました.  

`Queue2` では `object Queue2` の `empty` が TypeTag を必要としますが, `object Queue` は API の範囲外であるため許容範囲ではないかと考えました.  


### Reason

I modified TypeTag of `Queue` from `[T]` to `[+T]` in first `Queue`.  
Additionally, added `enQueue` TypeTag because `Queue` became covariant.  

So strictly speaking, the interface of the API changes, so I created another answer `Queue2`.  

In spite of `empty` of `object Queue2` require TypeTag in `Queue2`, I thought that is within the allowable range for excluding `object Queue` from the interface of the API.  

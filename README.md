# java-convenience-store-precourse

#  🏪 편의점 🏪

***
## 프로그램 개요
**편의점의 재고를 파악하고** 프로모션 **또는** 멤버십**을 적용하여 물건을 구매하는 프로그램이에요!**

***

```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[에너지바-5]

멤버십 할인을 받으시겠습니까? (Y/N)
Y 

===========W 편의점=============
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
===========증	정=============
콜라		1
==============================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
N
```

# 📜 기능 명세서 📜

***

### 입출력
- [x] 재고 출력하기
- [x] **상품 이름** 및 **개수** 입력받기
- [x] 프로모션 개수 미달시 추가할지 입력받기 (Y or N)
- [ ] 프로모션 할인 적용안될시 구매 할지 안할지 입력받기 (Y or N)
- [ ] 멤버십 할인 적용할지 입력받기 (Y or N)
- [ ] 영수증 출력하기
- [ ] 추가 구매할지 물어보기 (Y or N)

### 주요 기능
- [x] 재고파일들을 이용하여 재고 등록
- [x] 구매하려는 상품이 정상적인지 판단하기
- [x] 구매하려는 물건의 프로모션 적용 유무 판단
- [x] 프로모션 적용되지만 사용자가 덜 가져온경우 판단
- [ ] 프로모션 적용 가능 상품에서 개수 미달인 상황에서 사용자가 추가를 요청한 경우 **구매 상품 추가**
- [ ] 프로모션 적용 상품의 프로모션 재고 미달 판단
- [ ] 재고 부족으로 프로모션 적용이 되지 않는 상황에서 사용자가 구매를 원치 않은 경우 **구매 상품 제거**
- [ ] 프로모션 적용의 할인 금액 계산
- [ ] 멤버십 할인 금액 계산
- [ ] 재고 갱신
- [ ] 잘못된 입력값에 대해 다시 입력받기

# 🤔 생각해볼것 🤔
- [ ] 똑같은 상품을 입력했을때는? ex) `[사이다-3],[사이다-2]`
- [ ] 가격이 int 범위를 넘어설 수 있음
***
# ❗️ 예외 상황 ❗

***
### 공통 예외
- [ ] **공백 입력** ex) ` `

### 구매 상품 및 개수 입력시
- [x] **[(상품명)-(개수)] 형식이 아닌 경우** ex) `{사이다-3}` `[3-사이다]` `[사이다]`
- [x] **구분자가 ','가 아닌 경우** ex) `[사이다-3].[콜라-3]` `[사이다-3] [콜라-3]`
- [x] **존재하지 않는 상품을 입력한 경우** ex) `[코카사이다-1]`
- [x] **재고를 초과하여 입력한 경우** ex) `[콜라-9999]` 

### 프로모션 재고 무료 추가 유무, 정가 구매 적용 유무, 멤버십 할인 적용 유무, 추가 구매 유무
- [x] Y 또는 N 말고 다른 문자 또는 숫자가 사용된 경우 (y, n과 같이 소문자 포함) ex) `y`, `yes`, `no`, `good`, `3`, `plz`
# preshoes-model

센서 모듈로부터 수집한 데이터를 가공하는 모델입니다.

## API

### FootData

다음 필드로 이루어진 엔티티 클래스입니다:

- `int num`: 샘플 번호
- `int[] l_pressure`: 좌측 발 샘플 (12개)
- `int[] r_pressure`: 우측 발 샘플 (12개)

### StaticPressureProcessor

정적인 상태에서 발에 가해지는 압력을 분석하는 객체입니다.

 해당 클래스에서는 양 발의 센서를 총 4개의 그룹으로 만들어 사용합니다. 각 그룹은 part1, part2, part3, part4 라고 칭하겠습니다.
 각 파트는 다음과 같이 나누었습니다.

<img src="https://user-images.githubusercontent.com/51154225/83109844-118f5e80-a0fd-11ea-8220-583452b3c40b.png" width="30%">

출처 : https://www.triadfoot.com/wp-content/uploads/2012/05/iStock_000014250049Medium.jpg

- part1 : index 0번 센서를 포함하고 있습니다.
- part2 : index 1, 2, 3, 4번 센서를 포함하고 있습니다.
- part3 : index 5, 6, 7번 센서를 포함하고 있습니다.
- part4 : index 8, 9, 10, 11번 센서를 포함하고 있습니다.

다음 메소드를 제공합니다:

- `public  void process(FootData[]d)`: 샘플을 제공받아 양 발의 모든 센서값을 파트별로 합하여 저장합니다.
- `public double[] getResult()`: 양 발의 앞/뒤 무게중심 편향, 양 발의 무게중심 편향, 질병예측에 사용될 양 발의 part4 압력 차를 가져옵니다.

### FootStepPressureProcessor

걸어가는 중 발에 가해지는 압력을 분석하는 객체입니다.

다음 메소드를 제공합니다:

- `public void process(FootData[] d)`: 샘플을 걸음 단위로 나누어 결과를 분석한 후 내부 필드에 저장합니다.
- `public ReturnDataType getResult()`: 위 메소드의 결과를 가져옵니다. `FootStepPressureProcessor.ReturnDataType` 참조.

### FootStepPressureProcessor.ReturnDataType

`FootStepPressureProcessor`의 결과를 나타내는 엔티티 객체입니다.

다음 필드를 포함합니다:

- `double []leftPressure`: 왼발의 한 걸음을 센서별로 10단계씩 나눈 값 
- `double []rightPressure` : 오른발의 한 걸음을 센서별로 10단계로 나눈 값
- `int step`: 걸음 수
- `double []sequentialPressure` : 걷는 동안의 양 발의 무게중심 변화 (0(왼)~1(오))
- `double []feetWeightBias`: 양 발의 무게중심 편향 (0(왼)~1(오), 총 10개의 값)

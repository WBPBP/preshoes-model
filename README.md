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

해당 클래스에서는 센서는 총 4개의 그룹으로 만들어 사용합니다.

다음 메소드를 제공합니다:

- `public  void process(FootData[]d)`: 샘플을 제공받아 두 발의 압력 차 및 각 발의 앞/뒤꿈치 압력 차를 계산하여 내부 필드에 저장합니다.
- `public double[] getResult()`: 위 메소드의 결과를 가져옵니다.

### FootStepPressureProcessor

걸어가는 중 발에 가해지는 압력을 분석하는 객체입니다.

다음 메소드를 제공합니다:

- `public void process(FootData[] d)`: 샘플을 걸음 단위로 나누어 분석한 후 결과를 내부 필드에 저장합니다.
- `public ReturnDataType getResult()`: 위 메소드의 결과를 가져옵니다. `FootStepPressureProcessor.ReturnDataType` 참조.

### FootStepPressureProcessor.ReturnDataType

`FootStepPressureProcessor`의 결과를 나타내는 엔티티 객체입니다.

다음 필드를 포함합니다:

- `double [][]pressure`: 양쪽 발의 한 걸음을 센서별로 10단계씩 나눈 값 
- `int cnt`: 걸음 수
- `double [][]sum`: 데이터 길이에 맞게 0초부터 30초씩 증가하는 시간과 30초씩 동안의 두 발의 압력 차 평균 값

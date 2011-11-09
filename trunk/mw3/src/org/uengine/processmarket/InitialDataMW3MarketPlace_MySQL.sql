insert into category (categoryId, categoryName, parentcategoryId)
values (0, '재무/회계', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '인사/급여', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '영업 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '생산 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '설비 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '구매 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '수출입 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '재고 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '품질 관리', -1);
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '기술 정보 관리', -1);


insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '일반 회계 관리', (select cat.categoryId from category as cat where cat.categoryName='재무/회계'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '세무 관리', (select cat.categoryId from category as cat where cat.categoryName='재무/회계'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '고정 자산 관리', (select cat.categoryId from category as cat where cat.categoryName='재무/회계'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '자금 관리', (select cat.categoryId from category as cat where cat.categoryName='재무/회계'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '예산 관리', (select cat.categoryId from category as cat where cat.categoryName='재무/회계'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '인사 관리', (select cat.categoryId from category as cat where cat.categoryName='인사/급여'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '급여 관리', (select cat.categoryId from category as cat where cat.categoryName='인사/급여'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '연말 정산', (select cat.categoryId from category as cat where cat.categoryName='인사/급여'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '퇴직 관리', (select cat.categoryId from category as cat where cat.categoryName='인사/급여'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '복리 후생', (select cat.categoryId from category as cat where cat.categoryName='인사/급여'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '목표 관리', (select cat.categoryId from category as cat where cat.categoryName='영업 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '주문 관리', (select cat.categoryId from category as cat where cat.categoryName='영업 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '출하 관리', (select cat.categoryId from category as cat where cat.categoryName='영업 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '매출 관리', (select cat.categoryId from category as cat where cat.categoryName='영업 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '생산 계획', (select cat.categoryId from category as cat where cat.categoryName='생산 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '생산 능력 계획', (select cat.categoryId from category as cat where cat.categoryName='생산 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '생산 지시', (select cat.categoryId from category as cat where cat.categoryName='생산 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '생산 실행', (select cat.categoryId from category as cat where cat.categoryName='생산 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '보전 관리', (select cat.categoryId from category as cat where cat.categoryName='설비 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '이력 관리', (select cat.categoryId from category as cat where cat.categoryName='설비 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '구매 요구', (select cat.categoryId from category as cat where cat.categoryName='구매 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '구매 발주', (select cat.categoryId from category as cat where cat.categoryName='구매 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '외주 관리', (select cat.categoryId from category as cat where cat.categoryName='구매 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '지급 결의', (select cat.categoryId from category as cat where cat.categoryName='구매 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '수출 관리', (select cat.categoryId from category as cat where cat.categoryName='수출입 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '수입 관리', (select cat.categoryId from category as cat where cat.categoryName='수출입 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '입고', (select cat.categoryId from category as cat where cat.categoryName='재고 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '출고', (select cat.categoryId from category as cat where cat.categoryName='재고 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '재고 이동', (select cat.categoryId from category as cat where cat.categoryName='재고 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '재고 평가', (select cat.categoryId from category as cat where cat.categoryName='재고 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '입고 검사', (select cat.categoryId from category as cat where cat.categoryName='품질 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '공정 검사', (select cat.categoryId from category as cat where cat.categoryName='품질 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '출하 검사', (select cat.categoryId from category as cat where cat.categoryName='품질 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '클레임 관리', (select cat.categoryId from category as cat where cat.categoryName='품질 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '품질 통계 분석', (select cat.categoryId from category as cat where cat.categoryName='품질 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '품질 기준 관리', (select cat.categoryId from category as cat where cat.categoryName='품질 관리'));

insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '설계 정보 관리', (select cat.categoryId from category as cat where cat.categoryName='기술 정보 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '설계 변경', (select cat.categoryId from category as cat where cat.categoryName='기술 정보 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '개발', (select cat.categoryId from category as cat where cat.categoryName='기술 정보 관리'));
insert into category (categoryId, categoryName, parentcategoryId)
values ((select max(ca.categoryId)+1 from category as ca), '요청 관리', (select cat.categoryId from category as cat where cat.categoryName='기술 정보 관리'));


insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values (0,
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'전표 관리',
'전표를 효과적으로 관리하는 프로세스 입니다.',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'결산 관리',
'결산을 효과적으로 관리하는 프로세스 입니다.',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
2);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='세무 관리'),
'부가세 관리',
'부가세를 효과적으로 관리하는 프로세스 입니다.',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
1);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='세무 관리'),
'법인세 관리',
'법인세를 효과적으로 관리하는 프로세스 입니다.',
1,
'uEngine',
50000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
5);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='고정 자산 관리'),
'자산 취득/변경 관리',
'자산 취득 및 변경을 효과적으로 관리하는 프로세스 입니다.',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='고정 자산 관리'),
'감가 상각 관리',
'감가 강각을 효과적으로 관리하는 프로세스 입니다.',
3,
'uEngine',
90000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
5);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='자금 관리'),
'받을 어음 관리',
'받을 어음을 효과적으로 관리하는 프로세스 입니다.',
2,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='자금 관리'),
'수표/지급 어음 관리',
'수표/지급 어음을 효과적으로 관리하는 프로세스 입니다.',
2,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='자금 관리'),
'차입금 관리',
'차입금을 효과적으로 관리하는 프로세스 입니다.',
3,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
1);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='자금 관리'),
'유가 증권 관리',
'유가 증권을 효과적으로 관리하는 프로세스 입니다.',
2,
'uEngine',
5000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
4);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='자금 관리'),
'자금 수지 관리',
'자금 수지를 효과적으로 관리하는 프로세스 입니다.',
2,
'uEngine',
20000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
1);



insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'구매 관리',
'구매 관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'기술정보관리',
'기술정보관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'생산관리계획',
'생산관리계획',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'설비관리',
'설비관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'수출입관리',
'수출입관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'영업관리',
'영업관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'인사_급여',
'인사_급여',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'재고관리',
'재고관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'재무_회계',
'재무_회계',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
insert into marketItem (itemId, categoryId, itemName, description, version, comCode, price, filePath, logoImageFilePath, imageFile1Path, regDate, starPoint)
values ( (select max(mk.itemId)+1 from marketItem as mk),
(select cat.categoryId from category as cat where cat.categoryName='일반 회계 관리'),
'품질관리',
'품질관리',
1,
'uEngine',
10000,
'fileSystem//uEngine//pn//1.zip',
'fileSystem//uEngine//pn//logo.gif',
'fileSystem//uEngine//pn//1.gif',
CURDATE(),
3);
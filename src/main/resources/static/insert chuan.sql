use module6_cafe_store;
INSERT INTO service_types (type_name,code, description)
VALUES ('Cà Phê','CF',
        'Sự kết hợp hoàn hảo giữa hạt cà phê Robusta & Arabica thượng hạng được trồng trên những vùng cao nguyên Việt Nam màu mỡ, qua những bí quyết rang xay độc đáo, Smart Coffee chúng tôi tự hào giới thiệu những dòng sản phẩm Cà phê mang hương vị đậm đà và tinh tế.');

INSERT INTO service_types (type_name,code, description)
VALUES ('Freeze','FZ',
        'Sảng khoái với thức uống đá xay phong cách Việt. Freeze là thức uống đá xay mát lạnh được pha chế từ những nguyên liệu thuần túy của Việt Nam.');

INSERT INTO service_types (type_name,code, description)
VALUES ('Trà','TEA', 'Hương vị tự nhiên, thơm ngon của Trà Việt với phong cách hiện đại tại Smart Coffee sẽ giúp bạn gợi mở vị giác của bản thân và tận hưởng một cảm giác thật khoan khoái, tươi mới.

');

INSERT INTO service_types (type_name,code, description)
VALUES ('Khác','OT', null);

#INSERT SẢN PHẨM
INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0001', 'PHIN ĐEN ĐÁ', 1, 29000.00, 'Dành cho những tín đồ cà phê đích thực! Hương vị cà phê truyền thống được phối trộn độc đáo tại Highlands. Cà phê đậm đà pha hoàn toàn từ Phin, cho thêm 1 thìa đường, một ít đá viên mát lạnh, tạo nên Phin Đen Đá mang vị cà phê đậm đà chất Phin. ', 'HLC_New_logo_5.1_Products__PHIN_DEN_DA.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0002', 'PHIN SỮA ĐÁ', 1, 29000.00, 'Hương vị cà phê Việt Nam đích thực! Từng hạt cà phê hảo hạng được chọn bằng tay, phối trộn độc đáo giữa hạt Robusta từ cao nguyên Việt Nam, thêm Arabica thơm lừng. Cà phê được pha từ Phin truyền thống, hoà cùng sữa đặc sánh và thêm vào chút đá tạo nên ly Phin Sữa Đá – Đậm Đà Chất Phin.', 'HLC_New_logo_5.1_Products__PHIN_SUADA.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0003', 'PHIN ĐEN NÓNG', 1, 29000.00, 'Dành cho những tín đồ cà phê đích thực! Hương vị cà phê truyền thống được phối trộn độc đáo tại Highlands. Cà phê đậm đà pha từ Phin, cho thêm 1 thìa đường, mang đến vị cà phê đậm đà chất Phin.', 'HLC_PHIN_DEN_NONG.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0004', 'PHIN SỮA NÓNG', 1, 29000.00, 'Hương vị cà phê Việt Nam đích thực! Từng hạt cà phê hảo hạng được chọn bằng tay, phối trộn độc đáo giữa hạt Robusta từ cao nguyên Việt Nam, thêm Arabica thơm lừng. Kết hợp với nước sôi từng giọt cà phê được chiết xuất từ Phin truyền thống, hoà cùng sữa đặc sánh tạo nên ly Phin Sữa Nóng – Đậm đà chất Phin.', 'HLC__PHIN_SUA_NONG.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0005', 'PHINDI CASSIA', 1, 55000.00, 'Với chất phin êm ái, hương vị cà phê Việt Nam hiện đại kết hợp cùng hương quế nhẹ nhàng và thạch cà phê hấp dẫn.', 'Phindi_Cassia_Highlands_products_Image1.jpg', '00:05:00', 'available');

 INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0006', 'PHINDI CHOCO', 1, 45000.00, 'PhinDi Choco - Cà phê Phin thế hệ mới với chất Phin êm hơn, kết hợp cùng Choco ngọt tan mang đến hương vị mới lạ, không thể hấp dẫn hơn!', 'HLC_New_logo_5.1_Products__PHINDI_CHOCO.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0007', 'PHINDI HẠNH NHÂN', 1, 45000.00, 'PhinDi Hạnh Nhân - Cà phê Phin thế hệ mới với chất Phin êm hơn, kết hợp cùng Hạnh nhân thơm bùi mang đến hương vị mới lạ, không thể hấp dẫn hơn!', 'HLC_New_logo_5.1_Products__PHINDI_HANH_NHAN.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0008', 'PHINDI KEM SỮA', 1, 45000.00, 'PhinDi Kem Sữa - Cà phê Phin thế hệ mới với chất Phin êm hơn, kết hợp cùng Kem Sữa béo ngậy mang đến hương vị mới lạ, không thể hấp dẫn hơn!', 'HLC_New_logo_5.1_Products__PHINDI_KEM_SUA.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0009', 'BẠC XỈU ĐÁ', 1, 29000.00, 'Nếu Phin Sữa Đá dành cho các bạn đam mê vị đậm đà, thì Bạc Xỉu Đá là một sự lựa chọn nhẹ “đô" cà phê nhưng vẫn thơm ngon, chất lừ không kém!', 'HLC_New_logo_5.1_Products__BAC_XIU.jpg', '00:05:00', 'available');

 INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0010', 'LATTE', 1, 65000.00, 'Ly cà phê sữa ngọt ngào đến khó quên! Với một chút nhẹ nhàng hơn so với Cappuccino, Latte của chúng tôi bắt đầu với cà phê espresso, sau đó thêm sữa tươi và bọt sữa một cách đầy nghệ thuật. Bạn có thể tùy thích lựa chọn uống nóng hoặc dùng chung với đá.', 'HLC_New_logo_5.1_Products__LATTE_1.jpg', '00:05:00', 'available');

 INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time, status) VALUES ('CF0011', 'AMERICANO', 1, 45000.00, 'Americano tại Highlands Coffee là sự kết hợp giữa cà phê espresso thêm vào nước đun sôi. Bạn có thể tùy thích lựa chọn uống nóng hoặc dùng chung với đá.', 'HLC_New_logo_5.1_Products__AMERICANO_NONG.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('CF0012', 'LATTE TRÁI TIM NHỎ', 1, 40000.00, null, 'menu-1.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('CF0013', 'HOT CHOCOLATE', 1, 40000.00, null, 'menu-2.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('CF0014', 'CÀ PHÊ MẬT ONG', 1, 40000.00, null, 'menu-3.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('CF0015', 'LATTE TRÁI TIM NHỎ', 1, 35000.00, null, 'menu-4.jpg', '00:05:00', 'available');



INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('FZ0001', 'FREEZE SÔ-CÔ-LA', 2, 55000.00,
        'Thiên đường đá xay sô cô la! Từ những thanh sô cô la Việt Nam chất lượng được đem xay với đá cho đến khi mềm mịn, sau đó thêm vào thạch sô cô la dai giòn, ở trên được phủ một lớp kem whip beo béo và sốt sô cô la ngọt ngào. Tạo thành Freeze Sô-cô-la ngon mê mẩn chinh phục bất kì ai!',
        'HLC_New_logo_5.1_Products__FREEZE_CHOCO.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('FZ0002', 'FREEZE TRÀ XANH', 2, 55000.00,
        'Thức uống rất được ưa chuộng! Trà xanh thượng hạng từ cao nguyên Việt Nam, kết hợp cùng đá xay, thạch trà dai dai, thơm ngon và một lớp kem dày phủ lên trên vô cùng hấp dẫn. Freeze Trà Xanh thơm ngon, mát lạnh, chinh phục bất cứ ai!',
        'HLC_New_logo_5.1_Products__FREEZE_TRA_XANH.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('FZ0003', 'CARAMEL PHIN FREEZE', 2, 55000.00,
        'Thơm ngon khó cưỡng! Được kết hợp từ cà phê truyền thống chỉ có tại Highlands Coffee, cùng với caramel, thạch cà phê và đá xay mát lạnh. Trên cùng là lớp kem tươi thơm béo và caramel ngọt ngào. Món nước phù hợp trong những cuộc gặp gỡ bạn bè, bởi sự ngọt ngào thường mang mọi người xích lại gần nhau.',
        'HLC_New_logo_5.1_Products__CARAMEL_FREEZE_PHINDI.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('FZ0004', 'CLASSIC PHIN FREEZE', 2, 55000.00,
        'Thơm ngon đậm đà! Được kết hợp từ cà phê pha Phin truyền thống chỉ có tại Highlands Coffee, cùng với thạch cà phê và đá xay mát lạnh. Trên cùng là lớp kem tươi thơm béo và bột ca cao đậm đà. Món nước hoàn hảo để khởi đầu câu chuyện cùng bạn bè.',
        'HLC_New_logo_5.1_Products__CLASSIC_FREEZE_PHINDI.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('FZ0005', 'COOKIES & CREAM', 2, 55000.00,
        'Một thức uống ngon lạ miệng bởi sự kết hợp hoàn hảo giữa cookies sô cô la giòn xốp cùng hỗn hợp sữa tươi cùng sữa đặc đem say với đá viên, và cuối cùng không thể thiếu được chính là lớp kem whip mềm mịn cùng cookies sô cô la say nhuyễn.',
        'HLC_New_logo_5.1_Products__COOKIES_FREEZE.jpg', '00:05:00', 'available');
INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('TEA001', 'TRÀ XANH ĐẬU ĐỎ', 3, 45000.00, null, 'HLC_New_logo_5.1_Products__TRA_XANH_DAU_DO.jpg', '00:05:00',
        'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('TEA002', 'TRÀ SEN VÀNG (CỦ NĂNG)', 3, 45000.00,
        'Thức uống chinh phục những thực khách khó tính! Sự kết hợp độc đáo giữa trà Ô long, hạt sen thơm bùi và củ năng giòn tan. Thêm vào chút sữa sẽ để vị thêm ngọt ngào.',
        'HLC_New_logo_5.1_Products__TRA_SEN_VANG_CU_NANG.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('TEA003', 'TRÀ SEN VÀNG (SEN)', 3, 45000.00, null, 'HLC_New_logo_5.1_Products__TSV.jpg', '00:05:00',
        'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('TEA004', 'TRÀ THẠCH VẢI', 3, 45000.00,
        'Một sự kết hợp thú vị giữa trà đen, những quả vải thơm ngon và thạch giòn khó cưỡng, mang đến thức uống tuyệt hảo!',
        'HLC_New_logo_5.1_Products__TRA_TACH_VAI.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('TEA005', 'TRÀ THẠCH ĐÀO', 3, 45000.00,
        'Vị trà đậm đà kết hợp cùng những miếng đào thơm ngon mọng nước cùng thạch đào giòn dai. Thêm vào ít sữa để gia tăng vị béo.',
        'HLC_New_logo_5.1_Products__TRA_THANH_DAO-09.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('OT001', 'TRUYỀN THỐNG 1KG', 4, 375000.00, 'Sự kết hợp hài hòa giữa Arabica và Robusta.

Vị đậm và ngọt đầy.

Là sản phẩm đặc sản của của café Sữa Đá, Café Đá của hệ thống Highlands Coffee.

80% Robusta – 20% Arabica.', 'RG-TRADITIONAL-1kg-5.1.png', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('OT002', 'HỘP BÁNH TRUNG THU', 4, 469000.00,
        '  Một mùa Trung thu đoàn viên lại đến, Highlands thương gửi đến bạn Bộ Sưu Tập “Giòn Giã Chuyện Trăng” bánh với 4 hương vị mớ',
        'BOX_FULL__OPEN.png', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('OT003', 'TRUYỀN THỐNG 200GR', 4, 85000.00,
        'Cà phê truyền thống độc quyền của Highlands! Những hạt cà phê Robusta và Arabica thượng hạng trồng ở vùng cao nguyên của Việt Nam được rang và phối trộn theo công thức độc đáo tại Highlands.',
        'HLC_New_logo_5.1_Products__R_G_SANH_DIEU2.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('OT004', 'CULI 200GR', 4, 120000.00,
        'Hạt cà phê thượng hạng trồng ở vùng cao nguyên của Việt Nam được rang và phối trộn theo công thức độc đáo tại Highlands. Cho một sự cân bằng giữa vị mạnh nhưng rất êm đềm (100% hạt cà phê Culi). Cà phê Highlands Coffee được đóng gói tiện lợi cho việc sử dụng và bảo quản. Không chỉ dùng trong gia đình, với bao bì lịch sự còn thích hợp làm quà tặng ý nghĩa dành cho người thân, bạn bè và đồng nghiệp.',
        'HLC_New_logo_5.1_Products__R_G_CULI.jpg', '00:05:00', 'available');

INSERT INTO services (service_code, service_name, type_id, price, description, image_url, wait_time,
                                         status)
VALUES ('OT005', 'MOKA 200GR', 4, 120000.00,
        'Với 100% hạt cà phê Moka thượng hạng trồng ở vùng cao nguyên của Việt Nam được rang và phối trộn theo công thức độc đáo tại Highlands Moka là loại hạt cà phê cao cấp nhất của Việt Nam, có giá trị dinh dưỡng và chất lượng tốt nhất Cà phê Highlands Coffee được đóng gói tiện lợi cho việc sử dụng và bảo quản. Không chỉ dùng trong gia đình, với bao bì lịch sự còn thích hợp làm quà tặng ý nghĩa dành cho người thân, bạn bè và đồng nghiệp.',
        'HLC_New_logo_5.1_Products__R_G_MOKA.jpg', '00:05:00', 'available');

#----------------------------------------------------------------------------------------------------------------------------
#Insert table
insert into tables (table_id, code, is_on, state)
values  (1, 'TB0001', true, 'GOOD'),
        (2, 'TB0002', true, 'GOOD'),
        (3, 'TB0003', true, 'GOOD'),
        (4, 'TB0004', true, 'GOOD'),
        (5, 'TB0005', true, 'GOOD'),
        (6, 'TB0006', true, 'GOOD'),
        (7, 'TB0007', true, 'GOOD'),
        (8, 'TB0008', false, 'ERROR'),
        (9, 'TB0009', false, 'ERROR'),
        (10, 'TB0010', true, 'GOOD');

#-----------------
#Insert role
insert into roles (role_id, description, role_name)
values  (1, null, 'ROLE_ADMIN'),
        (2, null, 'ROLE_EMPLOYEE');
#------------
#Insert position
insert into positions (position_id, description, position_name)
values  (1, 'VỊ TRÍ: QUẢN LÝ', 'QUẢN LÝ'),
        (2, 'VỊ TRÍ: NHÂN VIÊN TOÀN THỜI GIAN', 'NHÂN VIÊN FULL TIME'),
        (3, 'VỊ TRÍ: NHÂN VIÊN BÁN THỜI GIAN', 'NHÂN VIÊN PART TIME');
#------------
#Insert emoloyeee
insert into employees (employee_id, full_name, image_url, note, salary, position_id)
values  (1, 'VŨ HUY HOÀNG', 'MALE_EMP.jpg', null, 5000000.00, 2),
        (2, 'TRƯƠNG THÀNH LONG', 'MALE_EMP.jpg', null, 15000000.00, 1),
        (3, 'VŨ THỊ KIỀU ẢNH', 'FEMALE_EMP.jpg', null, 3000000.00, 3),
        (4, 'TRẦN QUANG TRƯỜNG', 'MALE_EMP.jpg', null, 5000000.00, 2),
        (5, 'ĐOÀN VĂN QUYỀN', 'MALE_EMP.jpg', null, 4000000.00, 2),
        (6, 'NGUYỄN VĂN TÍNH', 'MALE_EMP.jpg', null, 5000000.00, 2);
        
#------------------------
#Insert user
insert into users (user_id, address, birthday, email, full_name, gender, is_verified, password, password_expiry_date, tel, username, verification_token, employee_id)
values  (1, 'Ba Đình, Hà Nội', '1997-11-18', 'hoangvhdata@gmail.com', 'Vũ Huy Hoàng', 'male', 0, '', null, '0329193149', 'hoangvu1811', null, 1);

#Feebback
insert into feedbacks (feedback_id, content, email, feedback_date, creator_id)
values  (1, 'Chất lượng đồ ăn tốt, giá cả hợp lý', 'abc1234@gmail.com', '2024-09-04', 1),
        (2, 'Quán hôm nay đông, nhưng phục vụ vẫn chu đáo', 'ghjk5321@gmail.com', '2024-09-02', 1);
#Bill
insert into bills (bill_id, code, date_created, status, creator_id, table_id)
values  (1, 'BI001', '2024-09-04 17:08:59.000000', 'pending', 1, 1),
        (2, 'BI002', '2024-08-04 17:09:23.000000', 'pending', 1, 3),
        (3, 'BI003', '2024-06-07 17:09:59.000000', 'pending', 1, 2);
#Bill deltails
insert into bill_details (bill_detail_id, quantity, bill_id, service_id)
values  (1, 1, 1, 1),
        (2, 1, 1, 3),
        (3, 2, 1, 8),
        (4, 1, 1, 29),
        (5, 1, 1, 13),
        (6, 2, 1, 5),
        (7, 1, 2, 17),
        (8, 3, 2, 21),
        (9, 5, 3, 14);
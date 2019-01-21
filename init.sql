CREATE DATABASE hardcore;

USE hardcore;

CREATE TABLE IF NOT EXISTS City (
                                  id integer NOT NULL,
                                  name varchar(120) NOT NULL,
                                  countryCode character(3) NOT NULL,
                                  district varchar(120) NOT NULL,
                                  population integer NOT NULL
);

INSERT INTO City VALUES (1, 'Kabul', 'AFG', 'Kabol', 1780000);
INSERT INTO City VALUES (2, 'Qandahar', 'AFG', 'Qandahar', 237500);
INSERT INTO City VALUES (3, 'Herat', 'AFG', 'Herat', 186800);
INSERT INTO City VALUES (4, 'Mazar-e-Sharif', 'AFG', 'Balkh', 127800);
INSERT INTO City VALUES (5, 'Amsterdam', 'NLD', 'Noord-Holland', 731200);
INSERT INTO City VALUES (6, 'Rotterdam', 'NLD', 'Zuid-Holland', 593321);
INSERT INTO City VALUES (7, 'Haag', 'NLD', 'Zuid-Holland', 440900);
INSERT INTO City VALUES (8, 'Utrecht', 'NLD', 'Utrecht', 234323);
INSERT INTO City VALUES (9, 'Eindhoven', 'NLD', 'Noord-Brabant', 201843);
INSERT INTO City VALUES (10, 'Tilburg', 'NLD', 'Noord-Brabant', 193238);
INSERT INTO City VALUES (11, 'Groningen', 'NLD', 'Groningen', 172701);
INSERT INTO City VALUES (12, 'Breda', 'NLD', 'Noord-Brabant', 160398);
INSERT INTO City VALUES (13, 'Apeldoorn', 'NLD', 'Gelderland', 153491);
INSERT INTO City VALUES (14, 'Nijmegen', 'NLD', 'Gelderland', 152463);
INSERT INTO City VALUES (15, 'Enschede', 'NLD', 'Overijssel', 149544);
INSERT INTO City VALUES (16, 'Haarlem', 'NLD', 'Noord-Holland', 148772);
INSERT INTO City VALUES (17, 'Almere', 'NLD', 'Flevoland', 142465);
INSERT INTO City VALUES (18, 'Arnhem', 'NLD', 'Gelderland', 138020);
INSERT INTO City VALUES (19, 'Zaanstad', 'NLD', 'Noord-Holland', 135621);
INSERT INTO City VALUES (20, '´s-Hertogenbosch', 'NLD', 'Noord-Brabant', 129170);
INSERT INTO City VALUES (21, 'Amersfoort', 'NLD', 'Utrecht', 126270);
INSERT INTO City VALUES (22, 'Maastricht', 'NLD', 'Limburg', 122087);
INSERT INTO City VALUES (23, 'Dordrecht', 'NLD', 'Zuid-Holland', 119811);
INSERT INTO City VALUES (24, 'Leiden', 'NLD', 'Zuid-Holland', 117196);
INSERT INTO City VALUES (25, 'Haarlemmermeer', 'NLD', 'Noord-Holland', 110722);
INSERT INTO City VALUES (26, 'Zoetermeer', 'NLD', 'Zuid-Holland', 110214);
INSERT INTO City VALUES (27, 'Emmen', 'NLD', 'Drenthe', 105853);
INSERT INTO City VALUES (28, 'Zwolle', 'NLD', 'Overijssel', 105819);
INSERT INTO City VALUES (29, 'Ede', 'NLD', 'Gelderland', 101574);
INSERT INTO City VALUES (30, 'Delft', 'NLD', 'Zuid-Holland', 95268);
INSERT INTO City VALUES (31, 'Heerlen', 'NLD', 'Limburg', 95052);
INSERT INTO City VALUES (32, 'Alkmaar', 'NLD', 'Noord-Holland', 92713);
INSERT INTO City VALUES (33, 'Willemstad', 'ANT', 'Curaçao', 2345);
INSERT INTO City VALUES (34, 'Tirana', 'ALB', 'Tirana', 270000);
INSERT INTO City VALUES (35, 'Alger', 'DZA', 'Alger', 2168000);
INSERT INTO City VALUES (36, 'Oran', 'DZA', 'Oran', 609823);
INSERT INTO City VALUES (37, 'Constantine', 'DZA', 'Constantine', 443727);
INSERT INTO City VALUES (38, 'Annaba', 'DZA', 'Annaba', 222518);
INSERT INTO City VALUES (39, 'Batna', 'DZA', 'Batna', 183377);
INSERT INTO City VALUES (40, 'Sétif', 'DZA', 'Sétif', 179055);
INSERT INTO City VALUES (41, 'Sidi Bel Abbès', 'DZA', 'Sidi Bel Abbès', 153106);
INSERT INTO City VALUES (42, 'Skikda', 'DZA', 'Skikda', 128747);
INSERT INTO City VALUES (43, 'Biskra', 'DZA', 'Biskra', 128281);
INSERT INTO City VALUES (44, 'Blida (el-Boulaida)', 'DZA', 'Blida', 127284);
INSERT INTO City VALUES (45, 'Béjaïa', 'DZA', 'Béjaïa', 117162);
INSERT INTO City VALUES (46, 'Mostaganem', 'DZA', 'Mostaganem', 115212);
INSERT INTO City VALUES (47, 'Tébessa', 'DZA', 'Tébessa', 112007);
INSERT INTO City VALUES (48, 'Tlemcen (Tilimsen)', 'DZA', 'Tlemcen', 110242);
INSERT INTO City VALUES (49, 'Béchar', 'DZA', 'Béchar', 107311);
INSERT INTO City VALUES (50, 'Tiaret', 'DZA', 'Tiaret', 100118);
INSERT INTO City VALUES (51, 'Ech-Chleff (el-Asnam)', 'DZA', 'Chlef', 96794);
INSERT INTO City VALUES (52, 'Ghardaïa', 'DZA', 'Ghardaïa', 89415);
INSERT INTO City VALUES (53, 'Tafuna', 'ASM', 'Tutuila', 5200);
INSERT INTO City VALUES (54, 'Fagatogo', 'ASM', 'Tutuila', 2323);
INSERT INTO City VALUES (55, 'Andorra la Vella', 'AND', 'Andorra la Vella', 21189);
INSERT INTO City VALUES (56, 'Luanda', 'AGO', 'Luanda', 2022000);
INSERT INTO City VALUES (57, 'Huambo', 'AGO', 'Huambo', 163100);
INSERT INTO City VALUES (58, 'Lobito', 'AGO', 'Benguela', 130000);
INSERT INTO City VALUES (59, 'Benguela', 'AGO', 'Benguela', 128300);
INSERT INTO City VALUES (60, 'Namibe', 'AGO', 'Namibe', 118200);
INSERT INTO City VALUES (61, 'South Hill', 'AIA', '', 961);
INSERT INTO City VALUES (62, 'The Valley', 'AIA', '', 595);
INSERT INTO City VALUES (63, 'Saint John´s', 'ATG', 'St John', 24000);
INSERT INTO City VALUES (64, 'Dubai', 'ARE', 'Dubai', 669181);
INSERT INTO City VALUES (65, 'Abu Dhabi', 'ARE', 'Abu Dhabi', 398695);
INSERT INTO City VALUES (66, 'Sharja', 'ARE', 'Sharja', 320095);
INSERT INTO City VALUES (67, 'al-Ayn', 'ARE', 'Abu Dhabi', 225970);
INSERT INTO City VALUES (68, 'Ajman', 'ARE', 'Ajman', 114395);
INSERT INTO City VALUES (69, 'Buenos Aires', 'ARG', 'Distrito Federal', 2982146);
INSERT INTO City VALUES (70, 'La Matanza', 'ARG', 'Buenos Aires', 1266461);
INSERT INTO City VALUES (71, 'Córdoba', 'ARG', 'Córdoba', 1157507);
INSERT INTO City VALUES (72, 'Rosario', 'ARG', 'Santa Fé', 907718);
INSERT INTO City VALUES (73, 'Lomas de Zamora', 'ARG', 'Buenos Aires', 622013);
INSERT INTO City VALUES (74, 'Quilmes', 'ARG', 'Buenos Aires', 559249);
INSERT INTO City VALUES (75, 'Almirante Brown', 'ARG', 'Buenos Aires', 538918);
INSERT INTO City VALUES (76, 'La Plata', 'ARG', 'Buenos Aires', 521936);
INSERT INTO City VALUES (77, 'Mar del Plata', 'ARG', 'Buenos Aires', 512880);
INSERT INTO City VALUES (78, 'San Miguel de Tucumán', 'ARG', 'Tucumán', 470809);
INSERT INTO City VALUES (79, 'Lanús', 'ARG', 'Buenos Aires', 469735);
INSERT INTO City VALUES (80, 'Merlo', 'ARG', 'Buenos Aires', 463846);
INSERT INTO City VALUES (81, 'General San Martín', 'ARG', 'Buenos Aires', 422542);
INSERT INTO City VALUES (82, 'Salta', 'ARG', 'Salta', 367550);
INSERT INTO City VALUES (83, 'Moreno', 'ARG', 'Buenos Aires', 356993);
INSERT INTO City VALUES (84, 'Santa Fé', 'ARG', 'Santa Fé', 353063);
INSERT INTO City VALUES (85, 'Avellaneda', 'ARG', 'Buenos Aires', 353046);
INSERT INTO City VALUES (86, 'Tres de Febrero', 'ARG', 'Buenos Aires', 352311);
INSERT INTO City VALUES (87, 'Morón', 'ARG', 'Buenos Aires', 349246);
INSERT INTO City VALUES (88, 'Florencio Varela', 'ARG', 'Buenos Aires', 315432);
INSERT INTO City VALUES (89, 'San Isidro', 'ARG', 'Buenos Aires', 306341);
INSERT INTO City VALUES (90, 'Tigre', 'ARG', 'Buenos Aires', 296226);
INSERT INTO City VALUES (91, 'Malvinas Argentinas', 'ARG', 'Buenos Aires', 290335);
INSERT INTO City VALUES (92, 'Vicente López', 'ARG', 'Buenos Aires', 288341);
INSERT INTO City VALUES (93, 'Berazategui', 'ARG', 'Buenos Aires', 276916);
INSERT INTO City VALUES (94, 'Corrientes', 'ARG', 'Corrientes', 258103);
INSERT INTO City VALUES (95, 'San Miguel', 'ARG', 'Buenos Aires', 248700);
INSERT INTO City VALUES (96, 'Bahía Blanca', 'ARG', 'Buenos Aires', 239810);
INSERT INTO City VALUES (97, 'Esteban Echeverría', 'ARG', 'Buenos Aires', 235760);
INSERT INTO City VALUES (98, 'Resistencia', 'ARG', 'Chaco', 229212);
INSERT INTO City VALUES (99, 'José C. Paz', 'ARG', 'Buenos Aires', 221754);
INSERT INTO City VALUES (100, 'Paraná', 'ARG', 'Entre Rios', 207041);
INSERT INTO City VALUES (101, 'Godoy Cruz', 'ARG', 'Mendoza', 206998);
INSERT INTO City VALUES (102, 'Posadas', 'ARG', 'Misiones', 201273);
INSERT INTO City VALUES (103, 'Guaymallén', 'ARG', 'Mendoza', 200595);
INSERT INTO City VALUES (104, 'Santiago del Estero', 'ARG', 'Santiago del Estero', 189947);
INSERT INTO City VALUES (105, 'San Salvador de Jujuy', 'ARG', 'Jujuy', 178748);
INSERT INTO City VALUES (106, 'Hurlingham', 'ARG', 'Buenos Aires', 170028);
INSERT INTO City VALUES (107, 'Neuquén', 'ARG', 'Neuquén', 167296);
INSERT INTO City VALUES (108, 'Ituzaingó', 'ARG', 'Buenos Aires', 158197);
INSERT INTO City VALUES (109, 'San Fernando', 'ARG', 'Buenos Aires', 153036);
INSERT INTO City VALUES (110, 'Formosa', 'ARG', 'Formosa', 147636);
INSERT INTO City VALUES (111, 'Las Heras', 'ARG', 'Mendoza', 145823);
INSERT INTO City VALUES (112, 'La Rioja', 'ARG', 'La Rioja', 138117);
INSERT INTO City VALUES (113, 'San Fernando del Valle de Cata', 'ARG', 'Catamarca', 134935);
INSERT INTO City VALUES (114, 'Río Cuarto', 'ARG', 'Córdoba', 134355);
INSERT INTO City VALUES (115, 'Comodoro Rivadavia', 'ARG', 'Chubut', 124104);
INSERT INTO City VALUES (116, 'Mendoza', 'ARG', 'Mendoza', 123027);
INSERT INTO City VALUES (117, 'San Nicolás de los Arroyos', 'ARG', 'Buenos Aires', 119302);
INSERT INTO City VALUES (118, 'San Juan', 'ARG', 'San Juan', 119152);
INSERT INTO City VALUES (119, 'Escobar', 'ARG', 'Buenos Aires', 116675);
INSERT INTO City VALUES (120, 'Concordia', 'ARG', 'Entre Rios', 116485);
INSERT INTO City VALUES (121, 'Pilar', 'ARG', 'Buenos Aires', 113428);
INSERT INTO City VALUES (122, 'San Luis', 'ARG', 'San Luis', 110136);
INSERT INTO City VALUES (123, 'Ezeiza', 'ARG', 'Buenos Aires', 99578);
INSERT INTO City VALUES (124, 'San Rafael', 'ARG', 'Mendoza', 94651);
INSERT INTO City VALUES (125, 'Tandil', 'ARG', 'Buenos Aires', 91101);
INSERT INTO City VALUES (126, 'Yerevan', 'ARM', 'Yerevan', 1248700);
INSERT INTO City VALUES (127, 'Gjumri', 'ARM', 'irak', 211700);
INSERT INTO City VALUES (128, 'Vanadzor', 'ARM', 'Lori', 172700);
INSERT INTO City VALUES (129, 'Oranjestad', 'ABW', '', 29034);
INSERT INTO City VALUES (130, 'Sydney', 'AUS', 'New South Wales', 3276207);
INSERT INTO City VALUES (131, 'Melbourne', 'AUS', 'Victoria', 2865329);
INSERT INTO City VALUES (132, 'Brisbane', 'AUS', 'Queensland', 1291117);
INSERT INTO City VALUES (133, 'Perth', 'AUS', 'West Australia', 1096829);
INSERT INTO City VALUES (134, 'Adelaide', 'AUS', 'South Australia', 978100);
INSERT INTO City VALUES (135, 'Canberra', 'AUS', 'Capital Region', 322723);
INSERT INTO City VALUES (136, 'Gold Coast', 'AUS', 'Queensland', 311932);
INSERT INTO City VALUES (137, 'Newcastle', 'AUS', 'New South Wales', 270324);
INSERT INTO City VALUES (138, 'Central Coast', 'AUS', 'New South Wales', 227657);
INSERT INTO City VALUES (139, 'Wollongong', 'AUS', 'New South Wales', 219761);
INSERT INTO City VALUES (140, 'Hobart', 'AUS', 'Tasmania', 126118);
INSERT INTO City VALUES (141, 'Geelong', 'AUS', 'Victoria', 125382);
INSERT INTO City VALUES (142, 'Townsville', 'AUS', 'Queensland', 109914);
INSERT INTO City VALUES (143, 'Cairns', 'AUS', 'Queensland', 92273);
INSERT INTO City VALUES (144, 'Baku', 'AZE', 'Baki', 1787800);
INSERT INTO City VALUES (145, 'Gäncä', 'AZE', 'Gäncä', 299300);
INSERT INTO City VALUES (146, 'Sumqayit', 'AZE', 'Sumqayit', 283000);
INSERT INTO City VALUES (147, 'Mingäçevir', 'AZE', 'Mingäçevir', 93900);
INSERT INTO City VALUES (148, 'Nassau', 'BHS', 'New Providence', 172000);
INSERT INTO City VALUES (149, 'al-Manama', 'BHR', 'al-Manama', 148000);
INSERT INTO City VALUES (150, 'Dhaka', 'BGD', 'Dhaka', 3612850);
INSERT INTO City VALUES (151, 'Chittagong', 'BGD', 'Chittagong', 1392860);
INSERT INTO City VALUES (152, 'Khulna', 'BGD', 'Khulna', 663340);
INSERT INTO City VALUES (153, 'Rajshahi', 'BGD', 'Rajshahi', 294056);
INSERT INTO City VALUES (154, 'Narayanganj', 'BGD', 'Dhaka', 202134);
INSERT INTO City VALUES (155, 'Rangpur', 'BGD', 'Rajshahi', 191398);
INSERT INTO City VALUES (156, 'Mymensingh', 'BGD', 'Dhaka', 188713);
INSERT INTO City VALUES (157, 'Barisal', 'BGD', 'Barisal', 170232);
INSERT INTO City VALUES (158, 'Tungi', 'BGD', 'Dhaka', 168702);
INSERT INTO City VALUES (159, 'Jessore', 'BGD', 'Khulna', 139710);
INSERT INTO City VALUES (160, 'Comilla', 'BGD', 'Chittagong', 135313);
INSERT INTO City VALUES (161, 'Nawabganj', 'BGD', 'Rajshahi', 130577);
INSERT INTO City VALUES (162, 'Dinajpur', 'BGD', 'Rajshahi', 127815);
INSERT INTO City VALUES (163, 'Bogra', 'BGD', 'Rajshahi', 120170);
INSERT INTO City VALUES (164, 'Sylhet', 'BGD', 'Sylhet', 117396);
INSERT INTO City VALUES (165, 'Brahmanbaria', 'BGD', 'Chittagong', 109032);
INSERT INTO City VALUES (166, 'Tangail', 'BGD', 'Dhaka', 106004);
INSERT INTO City VALUES (167, 'Jamalpur', 'BGD', 'Dhaka', 103556);
INSERT INTO City VALUES (168, 'Pabna', 'BGD', 'Rajshahi', 103277);
INSERT INTO City VALUES (169, 'Naogaon', 'BGD', 'Rajshahi', 101266);
INSERT INTO City VALUES (170, 'Sirajganj', 'BGD', 'Rajshahi', 99669);
INSERT INTO City VALUES (171, 'Narsinghdi', 'BGD', 'Dhaka', 98342);
INSERT INTO City VALUES (172, 'Saidpur', 'BGD', 'Rajshahi', 96777);
INSERT INTO City VALUES (173, 'Gazipur', 'BGD', 'Dhaka', 96717);
INSERT INTO City VALUES (174, 'Bridgetown', 'BRB', 'St Michael', 6070);
INSERT INTO City VALUES (175, 'Antwerpen', 'BEL', 'Antwerpen', 446525);
INSERT INTO City VALUES (176, 'Gent', 'BEL', 'East Flanderi', 224180);
INSERT INTO City VALUES (177, 'Charleroi', 'BEL', 'Hainaut', 200827);
INSERT INTO City VALUES (178, 'Liège', 'BEL', 'Liège', 185639);
INSERT INTO City VALUES (179, 'Bruxelles [Brussel]', 'BEL', 'Bryssel', 133859);
INSERT INTO City VALUES (180, 'Brugge', 'BEL', 'West Flanderi', 116246);
INSERT INTO City VALUES (181, 'Schaerbeek', 'BEL', 'Bryssel', 105692);

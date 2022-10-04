clear;
data = load('2022-09_sensor-wind-p1.csv');

X = data(:, 1);
Y = data(:, 2);
z = data(:, 3);

m = length(Y);

figure;
plot3(X, Y, z, 'o');

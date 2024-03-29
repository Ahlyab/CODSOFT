import requests

def get_weather_data(api_key, city_name):  
    api_url = "http://api.openweathermap.org/data/2.5/weather"  
    params = {  
        "q": city_name,  
        "units": "metric",  
        "appid": api_key  
    }  
    response = requests.get(api_url, params=params)  
    data = response.json()  
    return data  


def get_weather_report(data):
    res = {"city":data['name']}
    res['country'] = data['sys']['country']
    res['windspeed'] = data['wind']['speed']
    res['temp'] = data['main']['temp']
    res['feels'] = data['main']['feels_like']
    res['humidity'] = data['main']['humidity']
    res['pressure'] = data['main']['pressure']
    res['weather'] = data['weather'][0]['main']
    res['description'] = data['weather'][0]['description']
    return res

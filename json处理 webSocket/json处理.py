import time , json
'''
def transferData(old_value,new_mounth,new_day):
    iso_format='%Y-%m-%dT%H:%M:%S.%fZ'
    normal_format='%Y-%m-%d'
    time_array=time.strftime(old_value,iso_format)
    right_time=time.strftime(normal_format,time_array)
    temp_month=right_time.replace('-12',new_mounth)
    temp_day = temp_month.replace('-31', new_day)

    return temp_day

def format_json(json_file, t_month,t_day):
    j = json.load(open(json_file))
    d={}
    for key in range(0,len(j)):
        print(key,j[key])
        d[transferData(j[key], '-' + t_month, '-' + t_day)] = j[key]
    return d
if __name__=='__main__':
    format_json('1.txt','11','20')
'''
def transfer_date(old_value, new_month, new_day):
    iso_formate = "%Y-%m-%dT%H:%M:%S.%fZ"
    normal_format = "%Y-%m-%d"
    time_array = time.strptime(old_value, iso_formate)
    right_time = time.strftime(normal_format, old_value)
    temp_month = right_time.replace('-12', new_month)
    temp_day = temp_month.replace('-31', new_day)
    return temp_day
def format_json(json_file, t_month, t_day):
    j = json.load(open(json_file))
    d = {}
    for key in range(0,len(j)):
        print(key, j[key])
        d[transfer_date(j[key], '-' + t_month, '-' + t_day)] = j[key]
    return d
if __name__=='__main__':
    format_json('1.txt','11','20')